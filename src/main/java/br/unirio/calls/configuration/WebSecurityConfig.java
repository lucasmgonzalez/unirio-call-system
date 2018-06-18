package br.unirio.calls.configuration;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.unirio.calls.domains.authentication.JWTLoginFilter;
import br.unirio.calls.domains.user.User;
import br.unirio.calls.domains.user.UserRepository;
import br.unirio.calls.domains.authentication.JWTAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/").permitAll().antMatchers(HttpMethod.POST, "/api/user").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll().and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = passwordEncoder();
		
		UserDetailsService userDetailService = userDetailsService();
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
		
		AuthenticationService authenticationService = authenticationService();
		authenticationService.setPasswordEncoder(encoder);
		auth.authenticationProvider(authenticationService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new LocalAccountUserDetailsService();
    }

    @Bean
    public AuthenticationService authenticationService() {
        AuthenticationService auth = new AuthenticationService();
        auth.setUserDetailsService(userDetailsService());
        return auth;
    }

    private class LocalAccountUserDetailsService implements UserDetailsService {
        @Autowired
        private UserRepository repository;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            User user = repository.findByEmailAddress(username);

            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }

            return user;
        }
    }

    private class AuthenticationService extends DaoAuthenticationProvider {
        @Autowired
        private UserRepository repository;

        @Override
        public Authentication authenticate(Authentication authentication) throws AuthenticationException {
            User user = this.repository.findByEmailAddress(authentication.getName());
            try {
                Authentication auth = super.authenticate(authentication);
                // Register Login
                this.repository.registerSuccessfulLogin(user);
                return auth;
            } catch (BadCredentialsException e) {
                // Register Fail
                this.repository.registerFailedLogin(user);
                throw e;
            } catch (LockedException e) {
                throw e;
            }
        }
    }
}
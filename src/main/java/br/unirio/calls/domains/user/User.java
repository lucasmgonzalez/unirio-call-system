package br.unirio.calls.domains.user;

import java.util.Collection;
import java.util.Collections;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.unirio.calls.domains.college_section.CollegeSection;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class User implements UserDetails{

    private int id;
    private String name;
    private String email;
    private String password;
    private String socialId;
    private String passwordToken;
    private DateTime passwordTokenDate;
    private int loginFailedAttempts;
    private boolean blocked;
    private DateTime lastLoginDate;
    private boolean isAdministrator;

    private Collection<CollegeSection> collegeSections;

    public String getUsername() {
        return this.email;
    }

    public Collection<GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	public boolean isAccountNonExpired() {
        return true;
    };

	public boolean isAccountNonLocked() {
        return !this.blocked;
    };

	public boolean isCredentialsNonExpired() {
        return true;
    };

	public boolean isEnabled(){
        return true;
    };

    public boolean isAdmin() {
        return this.isAdministrator();
    }
}
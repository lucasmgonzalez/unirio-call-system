package br.unirio.calls.domains.authentication;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import br.unirio.calls.configuration.Configuration;
import br.unirio.calls.domains.user.User;
import br.unirio.calls.domains.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Configurable
public class Guard {
    private UserRepository repository;
    
    private User authUser;
    
    @Autowired
    public Guard(UserRepository repository) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        this.authUser = this.repository.findByEmailAddress(authentication.getName());
    }

    public User getAuthenticatedUser() {
        return this.authUser;
    }

    public boolean isAdmin() {
        return this.authUser != null && this.authUser.isAdmin();
    }
}
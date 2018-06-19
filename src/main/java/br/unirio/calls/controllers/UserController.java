package br.unirio.calls.controllers;

import java.util.Random;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.calls.core.helper.RandomString;
import br.unirio.calls.domains.user.User;
import br.unirio.calls.domains.user.UserRepository;
import br.unirio.calls.requests.RegisterUserRequest;
import br.unirio.calls.requests.ResetPasswordRequest;
import br.unirio.calls.requests.ResetPasswordTokenRequest;
import br.unirio.calls.requests.UpdateUserRequest;
import br.unirio.calls.resources.UserResource;

@RestController
public class UserController {

    @Autowired
    protected UserRepository repository;

    @Autowired
    protected PasswordEncoder encoder;

    @GetMapping("/api/user/{id}")
    public UserResource retrieve(@PathVariable String id) {
        return new UserResource(this.repository.findById(Integer.parseInt(id)));
    }

    @PostMapping("/api/user")
    public UserResource register(@Valid @RequestBody RegisterUserRequest requestBody) throws Exception{
        User user = requestBody.buildUser();

        if (!this.repository.save(user)) {
            throw new Exception("Could not register model");
        }
        return new UserResource(user);
    }

    @GetMapping("/api/me")
    public UserResource me() throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (!authentication.isAuthenticated()) {
            throw new Exception("Not authenticated");
        }

        return new UserResource(this.repository.findByEmailAddress(authentication.getName()));
    }

    @PutMapping("/api/user/{id}")
    public UserResource update(@PathVariable String id,@Valid @RequestBody UpdateUserRequest requestBody) throws Exception {
        User user = requestBody.buildUser();
        
        user.setId(Integer.parseInt(id));

        // Check if user can update this profile (same user)

        if(!this.repository.save(user)) {
            throw new Exception("Could not update model");
        }
        
        return new UserResource(user);
    }

    @PostMapping("/api/user/reset-password-token")
    public void resetPasswordToken(@Valid @RequestBody ResetPasswordTokenRequest requestBody) {
        User user = this.repository.findByEmailAddress(requestBody.getEmail());
        
        String token = RandomString.generate(100);
        
        this.repository.attachResetPasswordToken(user, token);

        // Send email with token
        System.out.println("Reset Password token:" + token);
    }

    @PostMapping("/api/user/reset-password/{token}")
    public void resetPassword(@PathVariable String token, @Valid @RequestBody ResetPasswordRequest requestBody) {
        User user = this.repository.findByResetPasswordToken(token);

        String password = this.encoder.encode(requestBody.getPassword());

        this.repository.changePassword(user, password);
        this.repository.unblock(user);
        this.repository.detachResetPasswordToken(user);
    }
}
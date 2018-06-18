package br.unirio.calls.controllers;

import java.util.Random;

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
import br.unirio.calls.requests.ResetPasswordRequest;
import br.unirio.calls.requests.ResetPasswordTokenRequest;
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
    public UserResource register(@RequestBody User input) throws Exception{
        if (input.getId() != 0) {
            input.setId(0);
        }

        // Encoding Password
        input.setPassword(encoder.encode(input.getPassword()));
        
        if (!this.repository.save(input)) {
            throw new Exception("Could not register model");
        }
        return new UserResource(input);
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
    public UserResource update(@PathVariable String id, @RequestBody User input) throws Exception {
        input.setId(Integer.parseInt(id));

        // Check if user can update this profile (same user)

        if(!this.repository.save(input)) {
            throw new Exception("Could not update model");
        }
        
        return new UserResource(input);
    }

    @PostMapping("/api/user/reset-password-token")
    public void resetPasswordToken(@RequestBody ResetPasswordTokenRequest requestBody) {
        User user = this.repository.findByEmailAddress(requestBody.getEmail());
        
        String token = RandomString.generate(100);
        
        this.repository.attachResetPasswordToken(user, token);

        // Send email with token
        System.out.println("Reset Password token:" + token);
    }

    @PostMapping("/api/user/reset-password/{token}")
    public void resetPassword(@PathVariable String token, @RequestBody ResetPasswordRequest requestBody) {
        User user = this.repository.findByResetPasswordToken(token);

        String password = this.encoder.encode(requestBody.getPassword());

        this.repository.changePassword(user, password);
        this.repository.unblock(user);
        this.repository.detachResetPasswordToken(user);
    }
}
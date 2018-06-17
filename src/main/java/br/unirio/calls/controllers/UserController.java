package br.unirio.calls.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.calls.domains.user.User;
import br.unirio.calls.domains.user.UserRepository;
import br.unirio.calls.resources.UserResource;

@RestController
public class UserController {

    @Autowired
    protected UserRepository repository;

    @GetMapping("/api/user/{id}")
    public UserResource retrieve(@PathVariable String id) {
        return new UserResource(this.repository.findById(Integer.parseInt(id)));
    }

    @PostMapping("/api/user")
    public UserResource register(@RequestBody User input) throws Exception{
        if (input.getId() != 0) {
            input.setId(0);
        }
        
        if (!this.repository.save(input)) {
            throw new Exception("Could not register model");
        }
        return new UserResource(input);
    }

    @PutMapping("/api/user/{id}")
    public UserResource update(@PathVariable String id, @RequestBody User input) throws Exception{
        /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User authUser = this.repository.findByEmailAddress(authentication.getName()); */
        
        input.setId(Integer.parseInt(id));

        if(!this.repository.save(input)) {
            throw new Exception("Could not update model");
        }
        
        return new UserResource(input);
    }
}
package br.unirio.calls.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.calls.domains.user.UserRepository;
import br.unirio.calls.resources.UserResource;

@RestController
public class UserController {

    @Autowired
    protected UserRepository repository;

    @GetMapping("/user/{id}")
    public UserResource retrieve(@PathVariable String id) {
        return new UserResource(this.repository.findById(Integer.parseInt(id)));
    }
}
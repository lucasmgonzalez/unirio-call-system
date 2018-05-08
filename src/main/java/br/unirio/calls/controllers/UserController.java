package br.unirio.calls.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.calls.domains.user.UserFactory;
import br.unirio.calls.domains.user.UserRepository;
import br.unirio.calls.resources.UserResource;

@RestController
public class UserController {

    protected UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/user/{id}")
    public UserResource retrieve(@PathVariable String id) {
        return new UserResource(this.repository.findById(Integer.parseInt(id)));
    }
}
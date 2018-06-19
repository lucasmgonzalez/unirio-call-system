package br.unirio.calls.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.calls.domains.authentication.Guard;
import br.unirio.calls.domains.college_section.CollegeSection;
import br.unirio.calls.domains.college_section.CollegeSectionRepository;
import br.unirio.calls.domains.user.User;
import br.unirio.calls.domains.user.UserRepository;
import br.unirio.calls.resources.CollegeSectionResource;

@RestController
public class CollegeSectionController {

    @Autowired
    protected CollegeSectionRepository repository;

    @Autowired
    protected UserRepository userRepository;

    @GetMapping("/api/college-section/{id}")
    public CollegeSectionResource retrieve(@PathVariable String id) {
        return new CollegeSectionResource(this.repository.findById(Integer.parseInt(id)));
    }

    @PostMapping("/api/college-section")
    public CollegeSectionResource register(@Valid @RequestBody CollegeSection input) throws Exception{
        if (input.getId() != 0) {
            input.setId(0);
        }

        /* Guard guard = new Guard();

        // Check if user is admin
        if (!guard.isAdmin()) {
            throw new Exception("You do not have permission");
        } */

        if (!this.repository.save(input)) {
            throw new Exception("Could not register model");
        }

        return new CollegeSectionResource(input);
    }

    @PutMapping("/api/college-section/{id}")
    public CollegeSectionResource update(@PathVariable String id,@Valid @RequestBody CollegeSection input) throws Exception {
        input.setId(Integer.parseInt(id));

        // Check if user can update this section 

        if (!this.repository.save(input)) {
            throw new Exception("Could not update model");
        }

        return new CollegeSectionResource(input);
    }

    @PostMapping("/api/college-section/{id}/associateUser/{userId}")
    public CollegeSectionResource associateUser(@PathVariable String id, @PathVariable String userId) throws Exception {
        CollegeSection section = this.repository.findById(Integer.parseInt(id));
        User user = this.userRepository.findById(Integer.parseInt(userId));

        if (!this.repository.associateUser(section, user)) {
            throw new Exception("Could not associate user");
        }

        return new CollegeSectionResource(section);
    }
}
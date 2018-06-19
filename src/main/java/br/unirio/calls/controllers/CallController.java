package br.unirio.calls.controllers;

import java.awt.List;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.unirio.calls.domains.call.Call;
import br.unirio.calls.domains.call.CallField;
import br.unirio.calls.domains.call.CallFieldRepository;
import br.unirio.calls.domains.call.CallRepository;
import br.unirio.calls.requests.RegisterCallFieldRequest;
import br.unirio.calls.requests.RegisterCallRequest;
import br.unirio.calls.requests.UpdateCallFieldRequest;
import br.unirio.calls.requests.UpdateCallRequest;
import br.unirio.calls.resources.CallFieldResource;
import br.unirio.calls.resources.CallResource;

@RestController
public class CallController {

    @Autowired
    protected CallRepository repository;

    @Autowired
    protected CallFieldRepository fieldRepository;

    @GetMapping("/api/call/{id}")
    public CallResource retrieve(@PathVariable String id) {
        return new CallResource(this.repository.findById(Integer.parseInt(id)));
    }

    @PostMapping("/api/call")
    public CallResource register(@Valid @RequestBody RegisterCallRequest requestBody) throws Exception {
        Call call = requestBody.buildCall();

        if (!this.repository.save(call)) {
            throw new Exception("Could not register model");
        }
        
        return new CallResource(call);
    }

    @PutMapping("/api/call/{id}")
    public CallResource update(@PathVariable String id, @Valid @RequestBody UpdateCallRequest requestBody) throws Exception {
        Call call = requestBody.buildCall();
        
        call.setId(Integer.parseInt(id));

        if(!this.repository.save(call)) {
            throw new Exception("Could not update model");
        }
        
        return new CallResource(call);
    }

    /* @GetMapping("/api/call/{id}/fields")
    public Collection<CallFieldResource> retrieveFields(@PathVariable String id) {
        
    } */

    @PostMapping("/api/call/{callId}/field")
    public CallFieldResource registerField(@PathVariable String callId,@Valid @RequestBody RegisterCallFieldRequest requestBody) throws Exception{
        CallField field = requestBody.buildCallField();

        field.setCallId(Integer.parseInt(callId));
        
        if (!this.fieldRepository.save(field)) {
            throw new Exception("Could not register model");
        }
        
        return new CallFieldResource(field);
    }

    @PutMapping("/api/call/{callId}/field/{id}")
    public CallFieldResource updateField(@PathVariable String id, @Valid @RequestBody UpdateCallFieldRequest requestBody) throws Exception{
        CallField field = requestBody.buildCallField();
        
        field.setId(Integer.parseInt(id));

        if(!this.fieldRepository.save(field)) {
            throw new Exception("Could not update model");
        }
        
        return new CallFieldResource(field);
    }

    @DeleteMapping("/api/call/{callId}/field/{id}")
    public void deleteField(@PathVariable String id) {
        // this.fieldRepository.delete(Integer.parseInt(id));
    }
}
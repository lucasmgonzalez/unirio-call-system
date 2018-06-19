package br.unirio.calls.core;

import java.util.HashMap;
import java.util.Map;

public class ApiError{
    protected Map<String, String> errors = new HashMap<String, String>();

    public void addError(String input, String message) {
        this.errors.put(input, message);
    }

    public String getError(String input) {
        return this.errors.get(input);
    }

    public Map<String, String> getAllErrors() {
        return this.errors;
    }
}
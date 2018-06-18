package br.unirio.calls.requests;

import lombok.Data;

public @Data class ResetPasswordRequest {
    private String password;
    private String passwordConfirmation;
}
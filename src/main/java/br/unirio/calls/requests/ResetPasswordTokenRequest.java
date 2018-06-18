package br.unirio.calls.requests;

import lombok.Data;

public @Data class ResetPasswordTokenRequest {
    private String email;
}
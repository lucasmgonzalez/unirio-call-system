package br.unirio.calls.requests;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

public @Data class ResetPasswordTokenRequest {
    
    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private String email;
}
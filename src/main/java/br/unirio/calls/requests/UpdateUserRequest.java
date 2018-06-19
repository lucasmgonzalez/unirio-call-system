package br.unirio.calls.requests;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.unirio.calls.domains.user.User;
import lombok.Data;

public @Data class UpdateUserRequest {
    
    @Autowired
    protected PasswordEncoder encoder;

    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    protected String name;
    
    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    @Email(message = "Email inválido")
    protected String email;

    public User buildUser() {
        User user = new User();

        user.setName(this.name);
        user.setEmail(this.email);

        return user;
    }
}
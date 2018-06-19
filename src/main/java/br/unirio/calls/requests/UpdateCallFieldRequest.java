package br.unirio.calls.requests;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.unirio.calls.domains.call.CallField;
import lombok.Data;

public @Data class UpdateCallFieldRequest {
    
    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    @Digits(message = "Precisa ser um número", fraction = 0, integer = 6)
    private int callId;
    
    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private String title;
    
    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    @Digits(message = "Precisa ser um número", fraction = 0, integer = 6)
    private int type;

    @Digits(message = "Precisa ser um número", fraction = 0, integer = 6)
    private int decimals = 0;

    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private boolean optional;

    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private String jsonOptions;

    public CallField buildCallField() {
        CallField field = new CallField();

        field.setCallId(this.callId);
        field.setTitle(this.title);
        field.setType(this.type);
        field.setDecimals(this.decimals);
        field.setOptional(this.optional);
        
        if (this.jsonOptions != null && !this.jsonOptions.isEmpty()) {
            field.setJsonOptions(this.jsonOptions);
        }

        return field;
    }
}
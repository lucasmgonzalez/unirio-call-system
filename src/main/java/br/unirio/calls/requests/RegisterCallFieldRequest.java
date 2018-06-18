package br.unirio.calls.requests;

import br.unirio.calls.domains.call.CallField;
import lombok.Data;

public @Data class RegisterCallFieldRequest {
    private int callId;
    private String title;
    private int type;
    private int decimals = 0;
    private boolean optional;
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
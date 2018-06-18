package br.unirio.calls.resources;

import java.util.Collection;

import br.unirio.calls.domains.call.CallField;
import br.unirio.calls.domains.call.CallFieldOption;
import lombok.Data;

public @Data class CallFieldResource {
    private int id;
    private int callId;
    private String title;
    private int type;
    private int decimals;
    private boolean isOptional;
    private Collection<CallFieldOption> options;

    public CallFieldResource(CallField field) {
        this.id = field.getId();
        this.callId = field.getCallId();
        this.title = field.getTitle();
        this.type = field.getType();
        this.decimals = field.getDecimals();
        this.isOptional = field.isOptional();

        if (field.getOptions() != null && !field.getOptions().isEmpty()) {
            this.options = field.getOptions();
        }
    }
}
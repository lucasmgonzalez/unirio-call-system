package br.unirio.calls.domains.call;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

public @Data class CallField {
    private int id;
    private int callId;
    private Call call;
    private String title;
    private int type;
    private int decimals;
    private boolean isOptional;
    private String jsonOptions;
    private Collection<CallFieldOption> options;

    public void setJsonOptions(String jsonOptions) {
        if (jsonOptions.isEmpty()) {
            return;
        }

        try {
            this.options = new ObjectMapper().readValue(jsonOptions, new TypeReference <ArrayList<CallFieldOption>>() {});
        } catch (Exception e) {
            System.out.println("Building options collection: " + e.getMessage());
        }
    }
}
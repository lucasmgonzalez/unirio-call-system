package br.unirio.calls.requests;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;

import br.unirio.calls.domains.call.Call;
import lombok.Data;

public @Data class RegisterCallRequest {
    
    @NotNull(message = "Não pode ser vazio")
    @Digits(message = "Precisa ser um número", fraction = 0, integer = 6)
    private int collegeSectionId;

    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private String name;

    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private String initials;
    
    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private String opensAt;

    @NotNull(message = "Não pode ser vazio")
    @NotEmpty(message = "Não pode ser vazio")
    private String closesAt;

    public Call buildCall() {
        Call call = new Call();

        DateTimeFormatter formatter = new DateTimeFormatterFactory("yyyy-MM-dd HH:mm:ss").createDateTimeFormatter();

        call.setCollegeSectionId(this.collegeSectionId);
        call.setName(this.name);
        call.setInitials(this.initials);
        call.setOpensAt(formatter.parseDateTime(this.opensAt));
        call.setClosesAt(formatter.parseDateTime(this.closesAt));

        return call;
    }
}
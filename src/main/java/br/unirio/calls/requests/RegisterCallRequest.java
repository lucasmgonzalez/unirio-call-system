package br.unirio.calls.requests;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.format.datetime.joda.DateTimeFormatterFactory;

import br.unirio.calls.domains.call.Call;
import lombok.Data;

public @Data class RegisterCallRequest {
    private int collegeSectionId;
    private String name;
    private String initials;
    private String opensAt;
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
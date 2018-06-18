package br.unirio.calls.resources;

import org.joda.time.DateTime;

import br.unirio.calls.domains.call.Call;
import lombok.Getter;

public class CallResource {
    private @Getter int id;
    private @Getter int collegeSectionId;
    //private @Getter CollegeSection section;
    private @Getter String name;
    private @Getter String initials;
    private @Getter String opensAt;
    private @Getter String closesAt;
    private @Getter boolean isCanceled;
    private @Getter boolean isFinished;

    public CallResource(Call call) {
        this.id = call.getId();
        this.collegeSectionId = call.getCollegeSectionId();
        this.name = call.getName();
        this.initials = call.getInitials();
        this.opensAt = call.getOpensAt() != null ? call.getOpensAt().toString("yyyy-MM-dd HH:mm:ss") : null;
        this.closesAt = call.getClosesAt() != null ? call.getClosesAt().toString("yyyy-MM-dd HH:mm:ss") : null;
        this.isCanceled = call.isCanceled();
        this.isFinished = call.isFinished();
    }
}
package br.unirio.calls.domains.call;

import org.joda.time.DateTime;

import br.unirio.calls.domains.college_section.CollegeSection;
import lombok.Data;

public @Data class Call {
    private int id;
	private DateTime registeredAt;
	private DateTime updatedAt;
    private int collegeSectionId;
    private CollegeSection section;
    private String name;
    private String initials;
    private DateTime opensAt;
    private DateTime closesAt;
    private boolean isCanceled;
    private boolean isFinished;
}
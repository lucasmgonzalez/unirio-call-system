package br.unirio.calls.resources;

import br.unirio.calls.domains.college_section.CollegeSection;
import lombok.Getter;

public class CollegeSectionResource {
    protected @Getter int id;
    protected @Getter String name;
    protected @Getter String initials;

    public CollegeSectionResource(CollegeSection section) {
        this.id = section.getId();
        this.name = section.getName();
        this.initials = section.getInitials();
    }
}
package br.unirio.calls.domains.college_section;

import org.joda.time.DateTime;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class CollegeSection {
    
    private int id;
    private DateTime registeredAt;
    private DateTime updatedAt;
    private String name;
    private String initials;

}
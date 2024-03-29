package br.unirio.calls.resources;

import java.util.ArrayList;
import java.util.Collection;

import br.unirio.calls.domains.college_section.CollegeSection;
import br.unirio.calls.domains.user.User;
import lombok.Getter;

public class UserResource {
    protected @Getter int id;
    protected @Getter String name;
    protected @Getter String email;
    protected @Getter String socialId;
    protected @Getter boolean blocked;
    protected @Getter String lastLoginDate;
    protected @Getter boolean isAdministrator;
    protected @Getter Collection<CollegeSectionResource> collegeSections;

    public UserResource(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.socialId = user.getSocialId();
        this.blocked = user.isBlocked();
        this.lastLoginDate = user.getLastLoginDate() != null ? user.getLastLoginDate().toString("yyyy-MM-dd HH:mm:ss") : null;
        this.isAdministrator  = user.isAdministrator();
        
        this.collegeSections = new ArrayList<>();
        for(CollegeSection collegeSection : user.getCollegeSections()) {
            this.collegeSections.add(new CollegeSectionResource(collegeSection));
        }

    }
}
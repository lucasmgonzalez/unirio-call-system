package br.unirio.calls.domains.college_section;

import br.unirio.calls.domains.user.User;

public interface CollegeSectionRepository {
    public CollegeSection findById(int id);

    public boolean save(CollegeSection section);

    public boolean associateUser(CollegeSection section, User user);
}
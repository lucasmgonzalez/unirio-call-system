package br.unirio.calls.resources;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

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

    public UserResource(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.socialId = user.getSocialId();
        this.blocked = user.isBlocked();
        this.lastLoginDate = user.getLastLoginDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        this.isAdministrator  =user.isAdministrator();
    }
}
package br.unirio.calls.domains.user;

import org.joda.time.DateTime;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public @Data class User {
    public static final String TABLE_NAME = "Usuario";

    private int id;
    private String name;
    private String email;
    private String password;
    private String socialId;
    private String passwordToken;
    private DateTime passwordTokenDate;
    private int loginFailedAttempts;
    private boolean blocked;
    private DateTime lastLoginDate;
    private boolean isAdministrator;
}
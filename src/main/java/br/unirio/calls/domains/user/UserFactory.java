package br.unirio.calls.domains.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import com.github.javafaker.Faker;

public class UserFactory {

    public static User buildFromResultSet(ResultSet rs) {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("nome"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("senha"));
            user.setSocialId(rs.getString("socialId"));
            user.setPasswordToken(rs.getString("tokenSenha"));
            user.setPasswordTokenDate(new DateTime(rs.getTimestamp("dataTokenSenha")));
            user.setLoginFailedAttempts(rs.getInt("falhasLogin"));
            user.setBlocked(rs.getBoolean("bloqueado"));
            user.setLastLoginDate(new DateTime(rs.getTimestamp("dataUltimoLogin")));
            user.setAdministrator(rs.getBoolean("administrador"));
        } catch (SQLException e) {
            // Log Errors
            User u = new User();
            u.setName("errinho");
            return u;
        }

        return user;
    }

    public static User buildFakeUser(int id) {
        Faker faker = new Faker();
        User user = new User();

        user.setId(id);
        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setAdministrator(false);
        user.setBlocked(false);
        user.setLastLoginDate(new DateTime(faker.date().past(10, TimeUnit.DAYS)));

        return user;
    }

}
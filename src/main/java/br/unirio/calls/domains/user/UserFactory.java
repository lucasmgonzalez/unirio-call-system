package br.unirio.calls.domains.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import com.github.javafaker.Faker;

public class UserFactory {

    public static User buildFromResultSet(ResultSet rs) {
        try {
            User user = new User();

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

            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("WTF FACTORY?!!");
            return null;
        }
    }

    public static User buildFakeUser(int id) {
        User user = buildFakeUser();

        user.setId(id);

        return user;
    }

    public static User buildFakeUser() {
        Faker faker = new Faker();
        User user = new User();

        user.setName(faker.name().firstName());
        user.setEmail(faker.internet().emailAddress());
        user.setAdministrator(false);
        user.setBlocked(false);
        user.setLastLoginDate(new DateTime(faker.date().past(10, TimeUnit.DAYS)));

        return user;
    }

}
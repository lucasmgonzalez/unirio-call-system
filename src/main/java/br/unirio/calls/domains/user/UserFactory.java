package br.unirio.calls.domains.user;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;

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
            return null;
        }

        return user;
    }

}
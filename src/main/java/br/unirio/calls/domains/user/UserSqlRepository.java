package br.unirio.calls.domains.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import br.unirio.calls.core.SqlRepository;

@Repository("UserRepository")
public class UserSqlRepository extends SqlRepository implements UserRepository {
    public static final String TABLE_NAME = "Usuario";

    public User findById(int id) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? UserFactory.buildFromResultSet(rs) : null;
        } catch (SQLException e) {
            return null;
        }
    }

    public User findByEmailAddress(String email) {
        if (this.connection == null)
            return null;

        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? UserFactory.buildFromResultSet(rs) : null;
        } catch (SQLException e) {
            return null;
        }
    }
}
package br.unirio.calls.domains.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.unirio.calls.core.AbstractDao;

public class UserDao extends AbstractDao {

    private final static String TABLE_NAME = "users";

    public User findById(int id) {
        Connection connection = getConnection();

        if (connection == null)
            return null;

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            return UserFactory.buildFromResultSet(rs);
        } catch (SQLException e) {
            return null;
        }
    }

}
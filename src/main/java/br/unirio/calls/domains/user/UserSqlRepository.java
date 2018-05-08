package br.unirio.calls.domains.user;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unirio.calls.core.SqlConnectionManager;

@Service("UserRepository")
public class UserSqlRepository implements UserRepository {
    private Connection connection;

    @Autowired
    public UserSqlRepository(SqlConnectionManager connectionManager) {
        this.connection = connectionManager.getConnection();
    }

    public User findById(int id) {
        if (this.connection == null) {
            User u = new User();
            u.setName("FUCK");
            return u;
        }

        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT * FROM " + User.TABLE_NAME + " WHERE id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            return UserFactory.buildFromResultSet(rs);
        } catch (SQLException e) {
            return null;
        }
    }

    public User findByEmailAddress(String email) {
        if (this.connection == null)
            return null;

        try {
            PreparedStatement ps = this.connection
                    .prepareStatement("SELECT * FROM " + User.TABLE_NAME + " WHERE email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            return UserFactory.buildFromResultSet(rs);
        } catch (SQLException e) {
            return null;
        }
    }
}
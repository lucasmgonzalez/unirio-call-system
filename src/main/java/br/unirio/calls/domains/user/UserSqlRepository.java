package br.unirio.calls.domains.user;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.concurrent.Callable;

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
        if (this.connection == null){
            System.out.println("WTF CONNECTION!!!??");
            return null;
        }

        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE email = ?");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return UserFactory.buildFromResultSet(rs);
            } else {
                System.out.println("WTF BROH?!");
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Not Found: " + e.getMessage());
            return null;
        }
    }

    public boolean save(User user)
	{	
        if (user.getId() == 0) {
            return this.register(user);
        }
		return this.update(user);
    }
    
    public boolean register(User user) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call RegistraUsuario(?, ?, ?, ?)}");
            cs.setString(1, user.getName());
            cs.setString(2, user.getEmail());
            cs.setString(3, user.getPassword());
            cs.registerOutParameter(4, Types.INTEGER);
            cs.execute();
            user.setId(cs.getInt(4));

            /* CallableStatement csRegisterPassword = this.connection.prepareCall("{call AtribuirSenha(?, ?)}");
            csRegisterPassword.setInt(1, user.getId());
            csRegisterPassword.setString(2, user.getPassword()); */

            return true;

        } catch (SQLException e)
        {
            System.out.println("UserRepository.register: " + e.getMessage());
            return false;
        }
    }

    public boolean update(User user) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call AlterarDadosUsuario(?, ?, ?)}");
            cs.setInt(1, user.getId());
            cs.setString(2, user.getName());
            cs.setString(3, user.getEmail());
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("UserRepository.update: " + e.getMessage());
            return false;
        }
    }

}
package br.unirio.calls.domains.call;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import org.springframework.stereotype.Repository;

import br.unirio.calls.core.SqlRepository;

@Repository("CallRepository")
public class CallSqlRepository extends SqlRepository implements CallRepository {
    public static final String TABLE_NAME = "Chamada";
    
    public Call findById(int id) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? CallFactory.buildFromResultSet(rs) : null;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean save(Call call) {
        if (call.getId() == 0) {
            return this.register(call);
        }

        return this.update(call);
    }

    public boolean register(Call call) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call CadastrarChamada(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, call.getCollegeSectionId());
            cs.setString(2, call.getName());
            cs.setString(3, call.getInitials());
            cs.setTimestamp(4, new Timestamp(call.getOpensAt().getMillis()));
            cs.setTimestamp(5, new Timestamp(call.getClosesAt().getMillis()));
            cs.registerOutParameter(6, Types.INTEGER);
            cs.execute();
            
            call.setId(cs.getInt(6));

            return true;

        } catch (SQLException e)
        {
            System.out.println("CallRepository.register: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Call call) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call AlterarChamada(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, call.getId());
            cs.setInt(2, call.getCollegeSectionId());
            cs.setString(3, call.getName());
            cs.setString(4, call.getInitials());
            cs.setTimestamp(5, new Timestamp(call.getOpensAt().getMillis()));
            cs.setTimestamp(6, new Timestamp(call.getClosesAt().getMillis()));
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("CallRepository.update: " + e.getMessage());
            return false;
        }
    }

    public boolean cancel(Call call) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call CancelarChamada(?)}");
            cs.setInt(1, call.getId());
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("CallRepository.cancel: " + e.getMessage());
            return false;
        }
    }

    public boolean close(Call call) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call EncerrarChamada(?)}");
            cs.setInt(1, call.getId());
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("CallRepository.close: " + e.getMessage());
            return false;
        }
    }
}
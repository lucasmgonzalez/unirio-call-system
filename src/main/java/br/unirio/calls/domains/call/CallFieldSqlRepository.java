package br.unirio.calls.domains.call;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.unirio.calls.core.SqlRepository;

@Repository("CallFieldRepository")
public class CallFieldSqlRepository extends SqlRepository implements CallFieldRepository {
    public static final String TABLE_NAME = "CampoChamada";
    
    public CallField findById(int id) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? CallFieldFactory.buildFromResultSet(rs) : null;
        } catch (SQLException e) {
            return null;
        }
    }

    public Collection<CallField> findByCallId(int callId) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE idChamada = ?");
            ps.setLong(1, callId);

            ResultSet rs = ps.executeQuery();

            ArrayList<CallField> collection = new ArrayList();
            
            while(rs.next()) {
                collection.add(CallFieldFactory.buildFromResultSet(rs));
            }
            
            return collection;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean save(CallField field) {
        if (field.getId() == 0) {
            return this.register(field);
        }

        return this.update(field);
    }

    public boolean register(CallField field) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call CadastrarCampo(?, ?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, field.getCallId());
            cs.setString(2, field.getTitle());
            cs.setInt(3, field.getType());
            cs.setInt(4, field.getDecimals());
            cs.setBoolean(5, field.isOptional());
            cs.setString(6, field.getJsonOptions());
            cs.registerOutParameter(7, Types.INTEGER);
            cs.execute();
            
            field.setId(cs.getInt(7));

            return true;

        } catch (SQLException e)
        {
            System.out.println("CallFieldRepository.register: " + e.getMessage());
            return false;
        }
    }

    public boolean update(CallField field) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call AlterarCampo(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, field.getId());
            cs.setString(2, field.getTitle());
            cs.setInt(3, field.getType());
            cs.setInt(4, field.getDecimals());
            cs.setBoolean(5, field.isOptional());
            cs.setString(6, field.getJsonOptions());
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("CallFieldRepository.update: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(CallField field) {
        return false;
        // Store Procedure not created
        /* if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call AlterarCampo(?, ?, ?, ?, ?, ?)}");
            cs.setInt(1, field.getId());
            cs.setString(2, field.getTitle());
            cs.setInt(3, field.getType());
            cs.setInt(4, field.getDecimals());
            cs.setBoolean(5, field.isOptional());
            cs.setString(6, field.getJsonOptions());
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("CallFieldRepository.update: " + e.getMessage());
            return false;
        } */
    }
}
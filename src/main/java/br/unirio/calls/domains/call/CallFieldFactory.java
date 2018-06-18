package br.unirio.calls.domains.call;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CallFieldFactory {
    public static CallField buildFromResultSet(ResultSet rs) {
        try {
            CallField field = new CallField();

            field.setId(rs.getInt("id"));
            field.setCallId(rs.getInt("idChamada"));
            field.setTitle(rs.getString("titulo"));
            field.setType(rs.getInt("type"));
            field.setDecimals(rs.getInt("decimais"));
            field.setOptional(rs.getBoolean("opcional"));
            field.setJsonOptions(rs.getString("jsonOptions"));

            return field;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
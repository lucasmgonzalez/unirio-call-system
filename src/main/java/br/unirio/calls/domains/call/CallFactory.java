package br.unirio.calls.domains.call;

import java.sql.ResultSet;

import org.joda.time.DateTime;

public class CallFactory {

    public static Call buildFromResultSet(ResultSet rs) {
        try {
            Call call = new Call();

            call.setId(rs.getInt("id"));
            call.setRegisteredAt(new DateTime(rs.getTimestamp("dataRegistro")));
            call.setUpdatedAt(new DateTime(rs.getTimestamp("dataAtualizacao")));
            call.setCollegeSectionId(rs.getInt("idUnidade"));
            call.setName(rs.getString("nome"));
            call.setInitials(rs.getString("sigla"));
            call.setOpensAt(new DateTime(rs.getTimestamp("dataAbertura")));
            call.setClosesAt(new Datetime(rs.getTimestamp("dataEncerramento")));
            call.setCanceled(rs.getBoolean("cancelada"));
            call.setFinished(rs.getBoolean("encerrada"));

            return call;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
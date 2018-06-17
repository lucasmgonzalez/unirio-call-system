package br.unirio.calls.domains.college_section;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.joda.time.DateTime;

public class CollegeSectionFactory {
    public static CollegeSection buildFromResultSet(ResultSet rs) {
        try {
            CollegeSection section = new CollegeSection();

            section.setId(rs.getInt("id"));
            section.setName(rs.getString("nome"));
            section.setInitials(rs.getString("sigla"));
            section.setRegisteredAt(new DateTime(rs.getTimestamp("dataRegistro")));
            section.setUpdatedAt(new DateTime(rs.getTimestamp("dataAtualizacao")));

            return section;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
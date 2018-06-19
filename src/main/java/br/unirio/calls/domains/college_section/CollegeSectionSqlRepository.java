package br.unirio.calls.domains.college_section;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Repository;

import br.unirio.calls.core.SqlRepository;
import br.unirio.calls.domains.user.User;

@Repository("CollegeSectionRepository")
public class CollegeSectionSqlRepository extends SqlRepository implements CollegeSectionRepository {
    public static final String TABLE_NAME = "UnidadeFuncional";

    public CollegeSection findById(int id) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? CollegeSectionFactory.buildFromResultSet(rs) : null;
        } catch (SQLException e) {
            return null;
        }
    }

    public Collection<CollegeSection> findByUserAssociated(User user) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT u.* FROM UnidadeFuncional u JOIN GestorUnidadeFuncional gu ON gu.idUnidade = u.id WHERE gu.idGestor = ?");
            ps.setLong(1, user.getId());

            ResultSet rs = ps.executeQuery();

            Collection<CollegeSection> collection = new ArrayList();

            while (rs.next()) {
                collection.add(CollegeSectionFactory.buildFromResultSet(rs));
            }

            return collection;
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean associateUser(CollegeSection section, User user) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call AssociarGestorUnidadeFuncional(?, ?)}");
            cs.setInt(1, user.getId());
            cs.setInt(2, section.getId());
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("CollegeSectionRepository.associateUser: " + e.getMessage());
            return false;
        }
    }

    public boolean save(CollegeSection section) {
        if (section.getId() == 0) {
            return this.register(section);
        }

        return this.update(section);
    }

    public boolean register(CollegeSection section) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call CadastrarUnidadeFuncional(?, ?, ?)}");
            cs.setString(1, section.getName());
            cs.setString(2, section.getInitials());
            cs.registerOutParameter(3, Types.INTEGER);
            cs.execute();
            section.setId(cs.getInt(3));

            return true;

        } catch (SQLException e)
        {
            System.out.println("CollegeSectionRepository.register: " + e.getMessage());
            return false;
        }
    }

    public boolean update(CollegeSection section) {
        if (this.connection == null)
            return false;
        
        try
        {
            CallableStatement cs = this.connection.prepareCall("{call AlterarUnidadeFuncional(?, ?, ?)}");
            cs.setInt(1, section.getId());
            cs.setString(2, section.getName());
            cs.setString(3, section.getInitials());
            cs.execute();

            return true;

        } catch (SQLException e)
        {
            System.out.println("CollegeSectionRepository.update: " + e.getMessage());
            return false;
        }
    }
}
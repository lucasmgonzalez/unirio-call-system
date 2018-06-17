package br.unirio.calls.domains.call;

import org.springframework.stereotype.Repository;

import br.unirio.calls.core.SqlRepository;

@Repository("CallRepository")
public class CallSqlRepository extends SqlRepository implements CallRepository {
    public static final String TABLE_NAME = "UnidadeFuncional";
    
    public Call findById(int id) {
        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM " + TABLE_NAME + " WHERE id = ?");
            ps.setLong(1, id);

            ResultSet rs = ps.executeQuery();

            return rs.next() ? CollegeSectionFactory.buildFromResultSet(rs) : null;
        } catch (SQLException e) {
            return null;
        }
    }
}
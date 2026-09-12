package ch.szclsb.test.springjdbc.api.jdbctempl;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.Sex;
import org.springframework.lang.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements CustomRowMapper<ContactRecord> {
    @Nullable
    @Override
    public ContactRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ContactRecord(
                rs.getLong("id"),
                rs.getLong("version"),
                rs.getBoolean("valid"),
                rs.getString("external_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                CustomRowMapper.toLocalDate(rs.getDate("date_of_birth")),
                CustomRowMapper.tryParseEnum(rs.getString("sex"), Sex.class).orElse(null),
                rs.getString("email"),
                rs.getString("phone"),
                rs.getString("language"),
                rs.getString("language_alternative"),
                rs.getString("street"),
                rs.getObject("postal_code", Integer.class),
                rs.getString("city"),
                rs.getString("country")
        );
    }
}

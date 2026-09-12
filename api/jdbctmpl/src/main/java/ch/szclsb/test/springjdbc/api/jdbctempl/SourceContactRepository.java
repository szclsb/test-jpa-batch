package ch.szclsb.test.springjdbc.api.jdbctempl;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SourceContactRepository {
    private static final RowMapper<ContactRecord> rowMapper = new ContactRowMapper();
    private final JdbcTemplate jdbcTemplate;

    public SourceContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public long count() {
        return jdbcTemplate.queryForObject("""
                SELECT COUNT(*)
                FROM source_contacts
                """, Long.class);
    }

    public List<ContactRecord> findAll() {
        return jdbcTemplate.query("""
                SELECT *
                FROM source_contacts
                """, rowMapper);
    }
}

package ch.szclsb.test.springjdbc.sync.jdbctmpl;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.TargetContactEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Types;
import java.util.List;

@Slf4j
@Repository
public class TargetContactRepository {
    private final JdbcTemplate jdbcTemplate;
    private final int batchSize;

    public TargetContactRepository(JdbcTemplate jdbcTemplate,
                                   @Value("${app.persistance.batch_size:1}") Integer batchSize) {
        this.jdbcTemplate = jdbcTemplate;
        this.batchSize = batchSize;
        log.debug("batch size = {}", batchSize);
    }

    public void deleteAll() {
        jdbcTemplate.execute("""
                    DELETE FROM target_contacts
                """);
    }

    public void saveAll(List<ContactRecord> contacts) {
        jdbcTemplate.batchUpdate("""
                        INSERT INTO target_contacts(id, version, valid, external_id, first_name, last_name, date_of_birth, sex, email, phone, language, language_alternative, street, postal_code, city, country)
                        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                        """, contacts, batchSize,
                (preparedStatement, record) -> {
                    preparedStatement.setLong(1, record.id());
                    preparedStatement.setLong(2, record.version());
                    preparedStatement.setBoolean(3, record.valid());
                    preparedStatement.setString(4, record.externalId());
                    preparedStatement.setString(5, record.firstName());
                    preparedStatement.setString(6, record.lastName());
                    preparedStatement.setDate(7, Date.valueOf(record.dateOfBirth()));
                    preparedStatement.setString(8, record.sex().name());
                    preparedStatement.setString(9, record.email());
                    preparedStatement.setString(10, record.phone());
                    preparedStatement.setString(11, record.language());
                    preparedStatement.setString(12, record.languageAlternative());
                    preparedStatement.setString(13, record.street());
                    preparedStatement.setObject(14, record.postalCode(), Types.INTEGER);
                    preparedStatement.setString(15, record.city());
                    preparedStatement.setString(16, record.country());
                });
    }
}

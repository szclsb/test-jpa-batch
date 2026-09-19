package ch.szclsb.test.springjdbc.sync.jdbcnamedtmpl;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.List;
import java.util.function.Consumer;

@Slf4j
@Repository
public class TargetContactRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final int batchSize;

    public TargetContactRepository(JdbcTemplate jdbcTemplate,
                                   @Value("${app.persistance.batch_size:1}") Integer batchSize) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        this.batchSize = batchSize;
        log.debug("batch size = {}", batchSize);
    }

    public void deleteAll() {
        jdbcTemplate.update("""
                    DELETE FROM target_contacts
                """, EmptySqlParameterSource.INSTANCE);
    }

    private void executeInBatch(List<ContactRecord> contacts, Consumer<SqlParameterSource[]> batchConsumer) {
        var n = contacts.size();
        var executions = n / batchSize + ((n % batchSize) > 0 ? 1 : 0);
        log.debug("records count {}, executions {}", n, executions);
        for (var execution = 0; execution < executions; execution++) {
            var batchOffset = execution * batchSize;
            var batchCount = Integer.min(batchSize, n - batchOffset);  // last batch may be smaller than batchSize
            var batch = new SqlParameterSource[batchCount];
            for (var i = 0; i < batchCount; i++) {
                var record = contacts.get(batchOffset + i);
                batch[i] = bind(record);
            }
            log.trace("executing batch {} with {} records", execution, batchCount);
            batchConsumer.accept(batch);  // todo async?
        }
    }

    public void saveAll(List<ContactRecord> contacts) {
        executeInBatch(contacts, batch -> jdbcTemplate.batchUpdate("""
                    INSERT INTO target_contacts(id, version, valid, external_id, first_name, last_name, date_of_birth, sex, email, phone, language, language_alternative, street, postal_code, city, country)
                    VALUES (:id, :version, :valid, :externalId, :firstName, :lastName, :dateOfBirth, :sex, :email, :phone, :language, :languageAlternative, :street, :postalCode, :city, :country)
                    """, batch));
    }

    private static SqlParameterSource bind(ContactRecord record) {
        return new MapSqlParameterSource()
                .addValue("id", record.id())
                .addValue("version", record.version())
                .addValue("valid", record.valid())
                .addValue("externalId", record.externalId())
                .addValue("firstName", record.firstName())
                .addValue("lastName", record.lastName())
                .addValue("dateOfBirth", Date.valueOf(record.dateOfBirth()))
                .addValue("sex", record.sex().name())
                .addValue("email", record.email())
                .addValue("phone", record.phone())
                .addValue("language", record.language())
                .addValue("languageAlternative", record.languageAlternative())
                .addValue("street", record.street())
                .addValue("postalCode", record.postalCode())
                .addValue("city", record.city())
                .addValue("country", record.country());
    }
}

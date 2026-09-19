package ch.szclsb.test.springjdbc.sync.jdbcoperations;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcInsertOperations;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
@Repository
public class TargetContactRepository {
    private final JdbcTemplate jdbcTemplate;
    private final SimpleJdbcInsertOperations jdbcInsert;
    private final int batchSize;

    public TargetContactRepository(JdbcTemplate jdbcTemplate,
                                   @Value("${app.persistance.batch_size:1}") Integer batchSize) {
        this.jdbcTemplate = jdbcTemplate;
        this.jdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("target_contacts");
        this.batchSize = batchSize;
        log.debug("batch size = {}", batchSize);
    }

    public void deleteAll() {
        jdbcTemplate.execute("""
                    DELETE FROM target_contacts
                """);
    }

    private <T> void executeInBatch(List<ContactRecord> contacts,
                                    Function<ContactRecord, SqlParameterSource> mapper,
                                    Consumer<SqlParameterSource[]> batchConsumer) {
        var n = contacts.size();
        var executions = n / batchSize + ((n % batchSize) > 0 ? 1 : 0);
        log.debug("records count {}, executions {}", n, executions);
        for (var execution = 0; execution < executions; execution++) {
            var batchOffset = execution * batchSize;
            var batchCount = Integer.min(batchSize, n - batchOffset);  // last batch may be smaller than batchSize
            var batch = new SqlParameterSource[batchCount];
            for (var i = 0; i < batchCount; i++) {
                var record = contacts.get(batchOffset + i);
                batch[i] = mapper.apply(record);
            }
            log.trace("executing batch {} with {} records", execution, batchCount);
            batchConsumer.accept(batch);  // todo async?
        }
    }

    public void saveAll(List<ContactRecord> contacts) {
        executeInBatch(contacts, TargetContactRepository::bind, jdbcInsert::executeBatch);
    }

    private static SqlParameterSource bind(ContactRecord record) {
        // column names
        return new MapSqlParameterSource()
                .addValue("id", record.id())
                .addValue("version", record.version())
                .addValue("valid", record.valid())
                .addValue("external_id", record.externalId())
                .addValue("first_name", record.firstName())
                .addValue("last_name", record.lastName())
                .addValue("date_of_birth", Date.valueOf(record.dateOfBirth()))
                .addValue("sex", record.sex().name())
                .addValue("email", record.email())
                .addValue("phone", record.phone())
                .addValue("language", record.language())
                .addValue("language_alternative", record.languageAlternative())
                .addValue("street", record.street())
                .addValue("postal_code", record.postalCode())
                .addValue("city", record.city())
                .addValue("country", record.country());
    }

//    private static Map<String, Object> bind(ContactRecord record) {
//        // column names
//        var map = new HashMap<String, Object>();
//        map.put("id", record.id());
//        map.put("version", record.version());
//        map.put("valid", record.valid());
//        map.put("external_id", record.externalId());
//        map.put("first_name", record.firstName());
//        map.put("last_name", record.lastName());
//        map.put("date_of_birth", Date.valueOf(record.dateOfBirth()));
//        map.put("sex", record.sex().name());
//        map.put("email", record.email());
//        map.put("phone", record.phone());
//        map.put("language", record.language());
//        map.put("language_alternative", record.languageAlternative());
//        map.put("street", record.street());
//        map.put("postal_code", record.postalCode());
//        map.put("city", record.city());
//        map.put("country", record.country());
//        return map;
//    }
}

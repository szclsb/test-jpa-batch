package ch.szclsb.test.springjdbc.api.jpa;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.Sex;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class SourceContactNativeRepository {
    private static final NativeTupleTransformer<ContactRecord> TRANSFORMER = new NativeTupleTransformer<>(rs -> new ContactRecord(
            rs.get("id", Long.class),
            rs.get("version", Long.class),
            rs.get("valid", Boolean.class),
            rs.get("external_id", String.class),
            rs.get("first_name", String.class),
            rs.get("last_name", String.class),
            NativeTupleTransformer.toLocalDate(rs.get("date_of_birth", Date.class)),
            NativeTupleTransformer.tryParseEnum(rs.get("sex", String.class), Sex.class).orElse(null),
            rs.get("email", String.class),
            rs.get("phone", String.class),
            rs.get("language", String.class),
            rs.get("language_alternative", String.class),
            rs.get("street", String.class),
            rs.get("postal_code", Integer.class),
            rs.get("city", String.class),
            rs.get("country", String.class)
    ));
    private final EntityManager entityManager;

    public SourceContactNativeRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ContactRecord> findAllAsRecords() {
        var query = TRANSFORMER.apply(entityManager.createNativeQuery("""
        SELECT *
        FROM source_contacts
        """));
        return query.getResultList();
    }
}

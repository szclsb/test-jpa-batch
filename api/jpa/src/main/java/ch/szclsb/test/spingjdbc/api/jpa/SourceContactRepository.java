package ch.szclsb.test.spingjdbc.api.jpa;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.SourceContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SourceContactRepository extends JpaRepository<SourceContactEntity, Long> {

    @Query("""
       SELECT new ch.szclsb.test.jpa.model.ContactRecord(
              c.id,
              c.version,
              c.valid,
              c.externalId,
              c.firstName,
              c.lastName,
              c.dateOfBirth,
              c.sex,
              c.email,
              c.phone,
              c.language,
              c.languageAlternative,
              c.street,
              c.postalCode,
              c.city,
              c.country
       )
       FROM SourceContactEntity c
       """)
    List<ContactRecord> findAllAsRecords();
}

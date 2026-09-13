package ch.szclsb.test.springjdbc.sync.jpabatch;

import ch.szclsb.test.springjdbc.model.TargetContactEntity;
import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class TargetContactRepository {
    private final EntityManager entityManager;
    private final int batchSize;

    public TargetContactRepository(EntityManager entityManager,
                                   @Value("${spring.jpa.properties.hibernate.jdbc.batch_size:1}") Integer batchSize) {
        this.entityManager = entityManager;
        this.batchSize = batchSize;
        log.debug("batch size = {}", batchSize);
    }

    @Modifying
    public int deleteAll() {
        var query = entityManager.createQuery("""
            DELETE FROM TargetContactEntity
        """);
        return query.executeUpdate();
    }

    @Modifying
    public void saveAll(List<TargetContactEntity> contacts) {
        var i = 0;
        for (var contact : contacts) {
            if ((i % batchSize) == 0) {
                entityManager.flush();
                entityManager.clear();
                log.trace("batch executed at {}", i);
            }
            entityManager.persist(contact);
            i += 1;
        }
    }
}

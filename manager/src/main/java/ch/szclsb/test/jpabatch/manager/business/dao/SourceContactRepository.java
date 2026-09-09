package ch.szclsb.test.jpabatch.manager.business.dao;

import ch.szclsb.test.jpabatch.model.SourceContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceContactRepository extends JpaRepository<SourceContactEntity, Long> {
}

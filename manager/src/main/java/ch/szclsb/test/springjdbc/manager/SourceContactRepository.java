package ch.szclsb.test.springjdbc.manager;

import ch.szclsb.test.jpa.model.SourceContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SourceContactRepository extends JpaRepository<SourceContactEntity, Long> {
}

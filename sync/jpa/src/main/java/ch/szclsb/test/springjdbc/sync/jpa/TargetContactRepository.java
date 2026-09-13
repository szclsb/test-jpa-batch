package ch.szclsb.test.springjdbc.sync.jpa;

import ch.szclsb.test.springjdbc.model.TargetContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TargetContactRepository extends JpaRepository<TargetContactEntity, Long> {
}

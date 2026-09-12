package ch.szclsb.test.spingjdbc.api.jpa;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.SourceContactEntity;
import ch.szclsb.test.springjdbc.monitor.ExecutionTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceContactService {
    private final SourceContactRepository repository;

    public SourceContactService(SourceContactRepository repository) {
        this.repository = repository;
    }

    @ExecutionTime
    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }

    @ExecutionTime
    @Transactional(readOnly = true)
    public List<SourceContactEntity> jpaFindAll() {
        return repository.findAll();
    }

    @ExecutionTime
    @Transactional(readOnly = true)
    public List<ContactRecord> recordFindAll() {
        return repository.findAllAsRecords();
    }
}

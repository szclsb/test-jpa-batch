package ch.szclsb.test.jpa.api.jdbctempl;

import ch.szclsb.test.jpa.model.ContactRecord;
import ch.szclsb.test.jpa.monitor.ExecutionTime;
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
    public List<ContactRecord> findAll() {
        return repository.findAll();
    }
}

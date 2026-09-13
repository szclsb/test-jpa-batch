package ch.szclsb.test.springjdbc.api.jpa;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.model.SourceContactEntity;
import ch.szclsb.test.springjdbc.monitor.ExecutionTime;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SourceContactService {
    private final SourceContactRepository repository;
    private final SourceContactNativeRepository nativeRepository;

    public SourceContactService(SourceContactRepository repository,
                                SourceContactNativeRepository nativeRepository) {
        this.repository = repository;
        this.nativeRepository = nativeRepository;
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

    @ExecutionTime
    @Transactional(readOnly = true)
    public List<ContactRecord> nativeFindAll() {
        return nativeRepository.findAllAsRecords();
    }
}

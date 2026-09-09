package ch.szclsb.test.jpabatch.manager.business.service;

import ch.szclsb.test.jpabatch.manager.business.dao.SourceContactRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SourceContactService {
    private final SourceContactRepository repository;

    public SourceContactService(SourceContactRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public long count() {
        return repository.count();
    }
}

package ch.szclsb.test.springjdbc.sync.jdbcnamedtmpl;

import ch.szclsb.test.springjdbc.model.ContactRecord;
import ch.szclsb.test.springjdbc.monitor.ExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class SyncService {
    private final TargetContactRepository repository;

    public SyncService(TargetContactRepository repository) {
        this.repository = repository;
    }

    @ExecutionTime
    @Transactional
    public void sync(List<ContactRecord> data) {
        log.info("syncing {} contacts", data.size());
        repository.deleteAll();
        log.debug("deleted start inserting");
        repository.saveAll(data);
        log.info("done");
    }
}

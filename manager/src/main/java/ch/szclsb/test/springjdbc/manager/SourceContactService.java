package ch.szclsb.test.springjdbc.manager;

import ch.szclsb.test.jpa.model.Sex;
import ch.szclsb.test.jpa.model.SourceContactEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

@Slf4j
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

    @Transactional
    public void generateTestData(int size) {
        // TODO use utils Generator
        log.info("generating {} test data", size);
        var sources = new ArrayList<SourceContactEntity>(size);
        for (var i = 0; i < size; i++) {
            var source = new SourceContactEntity();
            source.setValid(true);
            source.setExternalId("ext-%d".formatted(i));
            source.setFirstName("Max");
            source.setLastName("Muster");
            source.setDateOfBirth(LocalDate.now());
            source.setSex(Sex.MALE);
            source.setEmail("max.muster-%d@exmaple.com".formatted(i));
            source.setPhone("0791234567");
            source.setLanguage("de");
            source.setLanguageAlternative("fr");
            source.setStreet("Musterstrasse");
            source.setPostalCode(1234);
            source.setCity("Musterhausen");
            source.setCountry("CH");

            sources.add(source);
        }
        repository.saveAllAndFlush(sources);
        log.info("generated");
    }
}

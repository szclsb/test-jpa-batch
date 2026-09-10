package ch.szclsb.test.jpa.api.simple;

import ch.szclsb.test.jpa.model.SourceContactEntity;
import ch.szclsb.test.jpa.model.ContactRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SimpleApiController {
    private final SourceContactService service;

    public SimpleApiController(SourceContactService service) {
        this.service = service;
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @GetMapping("/jpa")
    public List<SourceContactEntity> jpaFindAll() {
        return service.jpaFindAll();
    }

    @GetMapping("/records")
    public List<ContactRecord> recordsFindAll() {
        return service.recordFindAll();
    }
}

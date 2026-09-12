package ch.szclsb.test.jpa.api.jdbctempl;

import ch.szclsb.test.jpa.model.ContactRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JdbcTemplateApiController {
    private final SourceContactService service;

    public JdbcTemplateApiController(SourceContactService service) {
        this.service = service;
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @GetMapping("/contact")
    public List<ContactRecord> findAll() {
        return service.findAll();
    }
}

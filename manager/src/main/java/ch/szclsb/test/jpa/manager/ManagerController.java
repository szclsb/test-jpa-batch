package ch.szclsb.test.jpa.manager;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private final SourceContactService service;

    public ManagerController(SourceContactService service) {
        this.service = service;
    }

    @GetMapping("/count")
    public long count() {
        return service.count();
    }

    @PostMapping("/generate")
    public void generate(@PathParam("size") Integer size) {
        service.generateTestData(size);
    }
}

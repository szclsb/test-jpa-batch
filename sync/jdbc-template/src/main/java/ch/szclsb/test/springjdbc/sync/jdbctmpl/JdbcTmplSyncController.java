package ch.szclsb.test.springjdbc.sync.jdbctmpl;

import ch.szclsb.test.springjdbc.sync.utils.ContactGenerator;
import jakarta.websocket.server.PathParam;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class JdbcTmplSyncController {
    private final SyncService syncService;
    private final TaskExecutor taskExecutor;

    public JdbcTmplSyncController(SyncService syncService,
                                  TaskExecutor taskExecutor) {
        this.syncService = syncService;
        this.taskExecutor = taskExecutor;
    }

    @PostMapping("/sync")
    public void sync(@PathParam("size") Integer size) {
        taskExecutor.execute(() -> {
            var data = ContactGenerator.generate(size);
            syncService.sync(data);
        });
    }
}

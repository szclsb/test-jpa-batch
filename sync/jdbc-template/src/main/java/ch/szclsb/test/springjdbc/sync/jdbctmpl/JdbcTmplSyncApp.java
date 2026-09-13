package ch.szclsb.test.springjdbc.sync.jdbctmpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class
})
@ComponentScan(basePackages = {
        "ch.szclsb.test.springjdbc.sync.jdbctmpl",
        "ch.szclsb.test.springjdbc.monitor"
})
@EntityScan(basePackages = {
        "ch.szclsb.test.springjdbc.model"
})
public class JdbcTmplSyncApp {
    public static void main(String[] args) {
        SpringApplication.run(JdbcTmplSyncApp.class, args);
    }
}

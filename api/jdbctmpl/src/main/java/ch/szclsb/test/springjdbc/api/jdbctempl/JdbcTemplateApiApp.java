package ch.szclsb.test.springjdbc.api.jdbctempl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
        UserDetailsServiceAutoConfiguration.class
})
@ComponentScan(basePackages = {
        "ch.szclsb.test.jpa.api.jdbctempl",
        "ch.szclsb.test.jpa.monitor"
})
public class JdbcTemplateApiApp {
    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateApiApp.class, args);
    }
}

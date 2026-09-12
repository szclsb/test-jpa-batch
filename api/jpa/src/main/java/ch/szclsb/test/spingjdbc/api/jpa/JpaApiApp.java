package ch.szclsb.test.spingjdbc.api.jpa;

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
        "ch.szclsb.test.jpa.api.simple",
        "ch.szclsb.test.jpa.monitor"
})
@EntityScan(basePackages = {
        "ch.szclsb.test.jpa.model"
})
public class JpaApiApp {
    public static void main(String[] args) {
        SpringApplication.run(JpaApiApp.class, args);
    }
}

package io.dakotawhipple.employeeservices;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
public class LoadDatabase {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repo) {
        return args -> {
            Employee e1 = new Employee("Wilson", "Tim", "J", "5/10/90", "5/23/19");
            Employee e2 = new Employee("Milson", "Jim", "R", "4/11/91", "5/23/19");
            log.info("Preloading " + repo.save(e1));
            log.info("Preloading " + repo.save(e2));
        };
    }
}

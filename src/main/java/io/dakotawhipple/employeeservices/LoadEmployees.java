package io.dakotawhipple.employeeservices;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

@Configuration
@Slf4j
public class LoadEmployees {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repo) {
        return args -> {
            try {
                Employee[] defaultEmployees = getDefaultEmployees();
                log.info("Preloading " + repo.saveAll(Arrays.asList(defaultEmployees)));
            } catch(FileNotFoundException e) {
                log.error("Unable to load default employees.");
            }
        };
    }

    //List<Employee> getDefaultEmployees() {
    Employee[] getDefaultEmployees() throws FileNotFoundException {
        String fileLocation = "default_employees.json";
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        File employeesFile;
        try {
            employeesFile = new File(classLoader.getResource(fileLocation).getFile());
        } catch(NullPointerException e) {
            throw new FileNotFoundException("Could not find " + fileLocation + " in /resources.");
        }

        Gson gson = new Gson();
        Employee[] defaultEmployees = gson.fromJson(new FileReader(employeesFile), Employee[].class);

        return defaultEmployees;
    }
}

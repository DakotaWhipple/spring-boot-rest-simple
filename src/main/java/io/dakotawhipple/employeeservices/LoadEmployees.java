package io.dakotawhipple.employeeservices;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.Arrays;

@Configuration
@Slf4j
public class LoadEmployees {
    private static final String EMPLOYEES_FILE = "default_employees.json";

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
    private Employee[] getDefaultEmployees() throws FileNotFoundException {
        Employee[] defaultEmployees;

        try {
            InputStream fileStream = getEmployeeFileStream();
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileStream));
            defaultEmployees = (new Gson()).fromJson(fileReader, Employee[].class);
        } catch(NullPointerException e) {
            throw new FileNotFoundException("Could not find " + EMPLOYEES_FILE + " in /resources.");
        }

        return defaultEmployees;
    }

    private InputStream getEmployeeFileStream() {
        ClassLoader classLoader = getClass().getClassLoader();
         // jar
        InputStream in = classLoader.getResourceAsStream("/" + EMPLOYEES_FILE);

        // ide
        if(in == null) {
            in = classLoader.getResourceAsStream(EMPLOYEES_FILE);
        }

        return in;
    }
}

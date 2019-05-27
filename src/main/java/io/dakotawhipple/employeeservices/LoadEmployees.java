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
        Employee[] defaultEmployees;
        ClassLoader classLoader = getClass().getClassLoader();

        try {
            // jar
            InputStream in = classLoader.getResourceAsStream("/" + fileLocation);

            // ide
            if(in == null) {
                in = classLoader.getResourceAsStream(fileLocation);
            }

            BufferedReader fileReader = new BufferedReader(new InputStreamReader(in));
            defaultEmployees = (new Gson()).fromJson(fileReader, Employee[].class);
        } catch(NullPointerException e) {
            throw new FileNotFoundException("Could not find " + fileLocation + " in /resources.");
        }

        return defaultEmployees;
    }
}

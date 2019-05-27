package io.dakotawhipple.employeeservices;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    private final EmployeeRepository repo;

    EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    // Aggregate root
    @GetMapping("/employees")
    List<Employee> all() {
        return repo.findAll().stream().filter(Employee::isActive).collect(Collectors.toList());
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repo.save(newEmployee);
    }

    // Single item
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repo.findById(id).filter(Employee::isActive)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repo.findById(id)
                .map(employee -> {
                    employee.setLastName(newEmployee.getLastName());
                    employee.setFirstName(newEmployee.getFirstName());
                    employee.setMiddleInitial(newEmployee.getMiddleInitial());
                    employee.setDateOfBirth(newEmployee.getDateOfBirth());
                    employee.setDateOfEmployment(newEmployee.getDateOfEmployment());
                    employee.setActive(newEmployee.isActive());
                    return repo.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repo.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repo.findById(id).map( employee -> {
            employee.setActive(false);
            return repo.save(employee);
        });
    }
}

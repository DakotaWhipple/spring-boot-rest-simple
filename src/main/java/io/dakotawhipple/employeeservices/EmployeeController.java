package io.dakotawhipple.employeeservices;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private final EmployeeRepository repo;

    EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    // Aggregate root
    @GetMapping("/employees")
    List<Employee> all() {
        return repo.findAll();
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repo.save(newEmployee);
    }

    // Single item
    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {
        return repo.findById(id)
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
        repo.deleteById(id);
    }
}

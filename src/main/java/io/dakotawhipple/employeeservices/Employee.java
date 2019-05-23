package io.dakotawhipple.employeeservices;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Employee {
    private @Id @GeneratedValue Long id;
    private String lastName;
    private String firstName;
    private String middleInitial;
    private String dateOfBirth;
    private String dateOfEmployment;
    private Boolean isActive;

    public Employee() {}

    public Employee(String lastName,
                    String firstName,
                    String middleInitial,
                    String dateOfBirth,
                    String dateOfEmployment,
                    Boolean isActive ) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.dateOfBirth = dateOfBirth;
        this.dateOfEmployment = dateOfEmployment;
        this.isActive = isActive;
    }
}


package io.dakotawhipple.employeeservices;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

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
    private String address;
    private String dateOfEmployment;
    @Builder.Default
    private boolean isActive = true;

    public Employee() {}

    public Employee(String lastName,
                    String firstName,
                    String middleInitial,
                    String dateOfBirth,
                    String address,
                    String dateOfEmployment,
                    boolean isActive) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.dateOfEmployment = dateOfEmployment;
        this.isActive = isActive;
    }
}


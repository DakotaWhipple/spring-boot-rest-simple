package io.dakotawhipple.employeeservices;

import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Employee {
    private @Id @GeneratedValue Long id;
    private String lastName;
    private String firstName;
    private String middleInitial;
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;
    private String address;
    @Temporal(TemporalType.DATE)
    private Date dateOfEmployment;
    @Builder.Default
    private boolean isActive = true;

    public Employee() {}

    public Employee(String lastName,
                    String firstName,
                    String middleInitial,
                    Date dateOfBirth,
                    String address,
                    Date dateOfEmployment,
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


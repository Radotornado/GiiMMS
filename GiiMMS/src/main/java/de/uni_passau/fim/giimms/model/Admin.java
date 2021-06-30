package de.uni_passau.fim.giimms.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

/**
 * An Admin is an Employee with additionally a list of all employees.
 */
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "admin")
public class Admin extends Employee {

    @ManyToMany
    private List<Employee> employees;

    /**
     * Constructor for an Admin.
     */
    public Admin(final String username, final String password,
                 final Coordinates coordinates, final String firstName,
                 final String lastName, final String position,
                 final List<Employee> employees) {
        this.username = username;
        this.password = password;
        this.coordinates = coordinates;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.status = true;
        this.isAvailable = true;
        this.availableTimeStart = System.currentTimeMillis();
        this.isAdmin = true;
        this.employees = employees;
    }
}

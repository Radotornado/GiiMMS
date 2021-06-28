package de.uni_passau.fim.giimms.model;

import de.uni_passau.fim.giimms.util.Coordinates;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "admin")
public class Admin extends Employee {

    @ManyToMany
    private Set<Employee> employees;

    public Admin(String username, String password, Coordinates coordinates,
                 String firstName, String lastName, String position,
                 Set<Employee> employees) {
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

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + username + '\'' +
                ", password='" + password + '\'' +
                ", coordinates=" + coordinates +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", availableTimeStart=" + availableTimeStart +
                ", availableTimeEnd=" + availableTimeEnd +
                ", status=" + status +
                ", isAvailable=" + isAvailable +
                ", isAdmin=" + isAdmin +
                ", list=" + employees +
                '}';
    }
}

package de.uni_passau.fim.giimms.model;

import de.uni_passau.fim.giimms.util.Coordinates;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password; // TODO: the OTP functionality
    @Embedded
    @Column(name = "coordinates")
    private Coordinates coordinates; // TODO: get them automatically
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "position", nullable = false)
    private String position;
    @Column(name = "availableTimeStart")
    private long availableTimeStart;
    @Column(name = "availableTimeEnd")
    private long availableTimeEnd;
    @Column(name = "status")
    private boolean status;
    @Column(name = "isAvailable")
    private boolean isAvailable;

    @ManyToMany
    private Set<Role> roles;


    public Employee(String email, String password, Coordinates coordinates,
                    String firstName, String lastName, String position) {
        this.email = email;
        this.password = password;
        this.coordinates = coordinates;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.status = true;
        this.isAvailable = true;
        this.availableTimeStart = System.currentTimeMillis();
    }

    private void stopTimer() {
        if (!isAvailable) {
            availableTimeEnd = System.currentTimeMillis();
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", coordinates=" + coordinates +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", position='" + position + '\'' +
                ", availableTimeStart=" + availableTimeStart +
                ", availableTimeEnd=" + availableTimeEnd +
                ", status=" + status +
                ", isAvailable=" + isAvailable +
                '}';
    }
}

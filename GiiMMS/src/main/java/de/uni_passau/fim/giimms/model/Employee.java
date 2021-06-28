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
    protected long id;
    @Column(name = "username", unique = true, nullable = false)
    protected String username;
    @Column(name = "password", nullable = false)
    protected String password; // TODO: the OTP functionality
    @Embedded
    @Column(name = "coordinates")
    protected Coordinates coordinates; // TODO: get them automatically
    @Column(name = "firstName", nullable = false)
    protected String firstName;
    @Column(name = "lastName", nullable = false)
    protected String lastName;
    @Column(name = "position", nullable = false)
    protected String position;
    @Column(name = "availableTimeStart")
    protected long availableTimeStart;
    @Column(name = "availableTimeEnd")
    protected long availableTimeEnd;
    @Column(name = "status")
    protected boolean status;
    @Column(name = "isAvailable")
    protected boolean isAvailable;
    @Column(name = "isAdmin")
    protected boolean isAdmin;

    @ManyToMany
    private Set<Role> roles;

    public Employee(String username, String password, Coordinates coordinates,
                    String firstName, String lastName, String position) {
        this.username = username;
        this.password = password;
        this.coordinates = coordinates;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.status = false;
        this.isAvailable = false;
        this.availableTimeStart = System.currentTimeMillis();
        this.isAdmin = false;
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
                '}';
    }

    public String getNames() {
        return firstName + " " + lastName;
    }
}

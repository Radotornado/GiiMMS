package de.uni_passau.fim.giimms.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password; // TODO: the OTP functionality
    @Embedded
    @Column(name = "coordinates", nullable = false)
    private Coordinates coordinates; // TODO: get them automatically
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "position", nullable = false)
    private String position;
    @Column(name = "availableTimeStart", nullable = false)
    private long availableTimeStart;
    @Column(name = "availableTimeEnd", nullable = false)
    private long availableTimeEnd;
    @Column(name = "status", nullable = false)
    private boolean status;
    @Column(name = "isAvailable", nullable = false)
    private boolean isAvailable;

    protected Employee() {
    }

    public Employee(String username, String password, Coordinates coordinates,
                    String firstName, String lastName, String position) {
        this.username = username;
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
                ", username='" + username + '\'' +
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

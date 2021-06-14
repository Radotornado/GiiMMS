package de.uni_passau.fim.giimms.util;

import lombok.Getter;
import lombok.Setter;

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
    private Long id;
    private String username; // TODO: check for uniqueness
    private String password; // TODO: the OTP functionality
    @Embedded
    private Coordinates coordinates; // TODO: get them automatically
    private String firstName;
    private String lastName;
    private String position;
    private long availableTimeStart; // column in the database
    private long availableTimeEnd; // column in the database
    private boolean status;
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

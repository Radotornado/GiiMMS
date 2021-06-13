package de.uni_passau.fim.giimms.util;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String username; // TODO: check for uniqueness
    @Getter
    private String password; // TODO: the OTP functionality
    @Getter
    @Setter
    private Coordinates coordinates; // TODO: get them automatically
    @Getter
    @Setter
    private String firstName;
    @Getter
    @Setter
    private String lastName;
    @Getter
    @Setter
    private String position;
    @Getter
    @Setter
    private long availableTimeStart; // column in the database
    @Getter
    @Setter
    private long availableTimeEnd; // column in the database
    @Getter
    @Setter
    private boolean status;
    @Getter
    @Setter
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
}

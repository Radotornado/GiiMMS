package de.uni_passau.fim.giimms.model;

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

/**
 * An Employee is an Entity of an employee in a firm. They have a unique
 * id and username, a password, coordinates, names, position, availability
 * and status.
 */
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
    @Column(name = "data")
    protected String data;

    @ManyToMany
    private Set<Role> roles;

    /**
     * Constructor for creating an Employee with all non-mandatory fields and
     * coordinates.
     */
    public Employee(final String username, final String password,
                    final Coordinates coordinates,
                    final String firstName, final String lastName,
                    final String position) {
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

    /**
     * Constructor for creating an Employee with all non-mandatory fields.
     */
    public Employee(final String username, final String password,
                    final String firstName, final String lastName,
                    final String position) {
        this.username = username;
        this.password = password;
        this.coordinates = null;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.status = false;
        this.isAvailable = false;
        this.availableTimeStart = System.currentTimeMillis();
        this.isAdmin = false;
    }

    /**
     * Stops the timer when the user logs out.
     */
    private void stopTimer() {
        if (!isAvailable) {
            availableTimeEnd = System.currentTimeMillis();
        }
    }

    /**
     * Returns the first and last name, separated by comma.
     *
     * @return The names.
     */
    public String getNames() {
        return firstName + " " + lastName;
    }

    /**
     * Prepares the object for exporting to json (because the other fields
     * are not meant to be exported with it).
     *
     * @return The Employee ready to be converted to JSON.
     */
    public String toJson() {
        return "Employee{"
                + "id: " + id
                + ", username: " + username
                + ", firstName: " + firstName
                + ", lastName: " + lastName
                + ", position: " + position
                + ", availability: " + isAvailable
                + ", latitude: " + coordinates.getLatitude()
                + ", longitude: " + coordinates.getLongitude()
                + ", status: " + status
                + ", data:" + data
                + "}";
    }
}

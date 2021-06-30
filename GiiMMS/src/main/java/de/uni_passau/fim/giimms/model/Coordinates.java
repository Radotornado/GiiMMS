package de.uni_passau.fim.giimms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * Saves the coordinates of an employee together.
 */
@Getter
@Setter
@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
public class Coordinates {

    private Double latitude;
    private Double longitude;

    /**
     * Outputs them together, separated by a comma.
     *
     * @return The coordinates.
     */
    @Override
    public String toString() {
        return latitude + ", " + longitude;
    }
}

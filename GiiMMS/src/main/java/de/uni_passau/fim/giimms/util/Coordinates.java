package de.uni_passau.fim.giimms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@Embeddable
@RequiredArgsConstructor
@AllArgsConstructor
public class Coordinates {

    private double latitude;
    private double longitude;

    @Override
    public String toString() {
        return latitude + "," + longitude;
    }
}

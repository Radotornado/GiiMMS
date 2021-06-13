package de.uni_passau.fim.giimms.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Coordinates {

    @Getter @Setter
    private double latitude;
    @Getter @Setter
    private double longitude;
}

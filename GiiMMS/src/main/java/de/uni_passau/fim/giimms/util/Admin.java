package de.uni_passau.fim.giimms.util;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long id;
    @Getter
    private String username; // TODO: check for uniqueness
    @Getter
    private String password; // TODO: the OTP functionality
    // TODO: list of employees
}

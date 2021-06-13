package de.uni_passau.fim.giimms;

import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.util.Coordinates;
import de.uni_passau.fim.giimms.util.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GiiMmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiiMmsApplication.class, args);
    }


}

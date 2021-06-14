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

    @Bean
    public CommandLineRunner demo(EmployeeRepository employeeRepo) {
        Logger log = LoggerFactory.getLogger(GiiMmsApplication.class);
        // test findAll()
        Coordinates testCoordinates = new Coordinates(48.566499, 13.4518444);
        Employee testEmployee1 = new Employee("hans", "123456", testCoordinates, "Hans", "Gall", "Sysadmin");
        Employee testEmployee2 = new Employee("flo", "654321", testCoordinates, "Florian", "Berninger", "Software Engineer");
        return (args) -> {
            employeeRepo.save(testEmployee1);
            employeeRepo.save(testEmployee2);
            for (Employee employee : employeeRepo.findAll()) {
                log.info(employee.toString());
            }
        };
    }
}

package de.uni_passau.fim.giimms;

import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.util.Coordinates;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class GiiMmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GiiMmsApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(EmployeeService employeeService) {
        //Logger log = LoggerFactory.getLogger(GiiMmsApplication.class);
        Coordinates testCoordinates1 = new Coordinates(48.566499, 13.4518444);
        Coordinates testCoordinates2 = new Coordinates(48.566349, 13.4511344);
        Employee testEmployee1 = new Employee("hans", "123456", testCoordinates1, "Hans", "Schiller", "Sysadmin");
        Employee testEmployee2 = new Employee("flo", "654321", testCoordinates2, "Florian", "Schuster", "Software Engineer");
        Employee testEmployee3 = new Employee("nina", "nina123", testCoordinates2, "Nina", "Schmidt", "QA Tester");
        Set<Employee> employees = new HashSet<>();
        employees.add(testEmployee1);
        employees.add(testEmployee2);
        employees.add(testEmployee3);
        Admin giimms = new Admin("giimms", "giimms", testCoordinates1, "Nicola", "Berninger", "CEO", employees);
        return (args) -> {
            employeeService.save(testEmployee1);
            employeeService.save(testEmployee2);
            employeeService.save(testEmployee3);
            employeeService.save(giimms);
            employeeService.saveAll(employees);
        };
    }
}

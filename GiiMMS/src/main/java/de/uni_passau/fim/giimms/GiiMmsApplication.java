package de.uni_passau.fim.giimms;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.util.Coordinates;
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
        //Logger log = LoggerFactory.getLogger(GiiMmsApplication.class);
        Coordinates testCoordinates1 = new Coordinates(48.566499, 13.4518444);
        Coordinates testCoordinates2 = new Coordinates(48.566349, 13.4511344);
        Employee testEmployee1 = new Employee("hans", "123456", testCoordinates1, "Hans", "Gall", "Sysadmin");
        Employee testEmployee2 = new Employee("flo", "654321", testCoordinates1, "Florian", "Berninger", "Software Engineer");
        Employee testEmployee3 = new Employee("nina", "nina123", testCoordinates2, "Nina", "Schmidt", "QA Tester");
        return (args) -> {
            employeeRepo.save(testEmployee1);
            employeeRepo.save(testEmployee2);
            employeeRepo.save(testEmployee3);
            //for (Employee employee : employeeRepo.findAll()) {
            //    log.info(employee.toString());
            //}
        };
    }
}

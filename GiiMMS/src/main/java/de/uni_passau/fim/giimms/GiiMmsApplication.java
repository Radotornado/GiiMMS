package de.uni_passau.fim.giimms;

import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.model.OfficialTerminal;
import de.uni_passau.fim.giimms.services.AdminService;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.model.Coordinates;
import de.uni_passau.fim.giimms.services.OfficialTerminalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Here lies the main function that runs he app and the creation of
 * a few demo Employees.
 */
@SpringBootApplication
public class GiiMmsApplication {

    /**
     * Main function that starts the application.
     *
     * @param args Java mandatory.
     */
    public static void main(final String[] args) {
        SpringApplication.run(GiiMmsApplication.class, args);
    }

    /**
     * A bean that creates a three example employees with all their data
     * and an admin.
     *
     * @param employeeService The employee service, needed for saving
     *                        the employees.
     * @param adminService    The admin service, needed for saving
     *                        the admin.
     */
    @Bean
    public CommandLineRunner demo(final EmployeeService employeeService,
                                  final AdminService adminService,
                                  final OfficialTerminalService officialTerminalService) {
        Employee hans = new Employee("hans", "123456",
                new Coordinates(48.566499, 13.4518444),
                "Hans", "Schiller", "Sysadmin");
        Employee flo = new Employee("flo", "654321",
                new Coordinates(48.566349, 13.4511344),
                "Florian", "Schuster", "Software Engineer");
        Employee nina = new Employee("nina", "nina123",
                new Coordinates(48.566349, 13.4511344),
                "Nina", "Schmidt", "QA Tester");
        List<Employee> employees
                = Stream.of(hans, flo, nina).collect(Collectors.toList());
        Admin giimms = new Admin("giimms", "giimms",
                new Coordinates(48.566499, 13.4518444),
                "Nicola", "Berninger", "CEO", employees);
        OfficialTerminal officialTerminal =
                new OfficialTerminal(new Coordinates(48.566499, 13.4518444));
        return (args) -> {
            employeeService.saveAll(employees);
            adminService.update(giimms);
            officialTerminalService.save(officialTerminal);
        };
    }
}

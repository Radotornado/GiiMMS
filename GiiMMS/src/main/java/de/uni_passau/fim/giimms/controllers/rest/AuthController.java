package de.uni_passau.fim.giimms.controllers.rest;

import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.util.Coordinates;
import de.uni_passau.fim.giimms.util.Employee;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/employees")
@AllArgsConstructor
public class AuthController {

    private final EmployeeRepository employeeRepository;

    @RequestMapping("/register")
    public String addEvent(@RequestBody Map<String, Object> map) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        double latitude = (double) map.get("latitude");
        double longitude = (double) map.get("longitude");
        Coordinates coordinates = new Coordinates(latitude, longitude);
        String firstName = (String) map.get("firstName");
        String lastName = (String) map.get("lastName");
        String position = (String) map.get("position");
        Employee employee = new Employee(username, password, coordinates,
                firstName, lastName, position);
        // todo handle errors
        employeeRepository.save(employee);
        return "redirect:/adminPage";
    }
}

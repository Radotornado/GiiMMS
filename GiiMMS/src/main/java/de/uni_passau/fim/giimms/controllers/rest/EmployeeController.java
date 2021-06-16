package de.uni_passau.fim.giimms.controllers.rest;

import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.util.Coordinates;
import de.uni_passau.fim.giimms.util.Employee;
import de.uni_passau.fim.giimms.util.EmployeeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeRepository employeeRepository;

    @PostMapping("/employees/register")
    public ModelAndView addEmployee(@RequestBody Map<String, Object> map) {
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
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage");
        return modelAndView;
    }

    @GetMapping("/employees/getEmployee/{id}")
    public ModelAndView showProfile(@PathVariable("id") int id, Model model) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Invalid id.");
        }
        model.addAttribute("employee", employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("employeeProfile");
        return modelAndView;
    }

}

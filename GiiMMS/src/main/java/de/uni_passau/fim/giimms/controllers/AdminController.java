package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class AdminController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/addEmployee")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "addEmployee";
        }

        model.addAttribute("employeeForm", new Employee());

        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String registration(@RequestParam("username") String username,
                               @RequestParam("firstName") String firstName,
                               @RequestParam("lastName") String lastName,
                               @RequestParam("password") String password,
                               @RequestParam("position") String position) {

        Employee employee = new Employee(username, password, firstName,
                lastName, position);
        employeeService.save(employee);
        return "redirect:/";
    }

    @GetMapping("/adminProfileView/{id:[1-9]+[0-9]*}")
    public String overviewEmployee(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", employeeService.findById(id));
        return "adminProfileView";
    }
}

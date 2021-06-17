package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.util.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/addEmployee")
public class RegistrationController {

    private final EmployeeService employeeService;

    @ModelAttribute("employee")
    public Employee employee() {
        return new Employee();
    }

    @GetMapping
    public String showRegistrationForm() {
        return "addEmployee";
    }

    @PostMapping
    public String registerEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/addEmployee?success";
    }
}

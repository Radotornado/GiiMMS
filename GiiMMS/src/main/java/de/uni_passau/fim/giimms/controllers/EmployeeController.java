package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EmployeeValidator employeeValidator;

    @GetMapping("/terminal")
    @PostMapping("/terminal")
    public String terminal(Model model, String error, String logout) {

        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Username or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "Logged out successfully.");

        return "/terminal";
    }

    @GetMapping("/")
    public String loggedIn(Model model, Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        if (employee.isAdmin()) {
            Admin admin = (Admin) employeeService.findByUsername(principal.getName());
            model.addAttribute("admin", admin);

            return "adminPanel";
        }
        model.addAttribute("employee", employee);
        return "employeePanel";
    }
}


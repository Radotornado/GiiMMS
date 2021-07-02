package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.AdminService;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.OTUPasswordService;
import de.uni_passau.fim.giimms.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controls the terminal (login).
 */
@Controller
public class TerminalController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private OTUPasswordService otuPasswordService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private SecurityService securityService;

    /**
     * Check if an Employee or Admin is authenticated and redirect accordingly.
     * Also works as auto login.
     */
    @RequestMapping(value = "/terminal",
            method = {RequestMethod.GET, RequestMethod.POST})
    public String terminal(final Model model, final String error,
                           final String logout) {
        // check if authenticated
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }
        // check if username or password is invalid
        if (error != null) {
            model.addAttribute("error", "Username or password is invalid.");
        }
        // check was it logged out
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "/terminal";
    }

    /**
     * Controls the homepage.
     */
    @GetMapping("/")
    public String loggedIn(Model model, Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        if (employee.getIsExpired()) {
            model.addAttribute("error", "One time use password expired. " +
                    "Contact an Administrator for more Information.");
            return "/terminal";
        }
        // check if and Admin is trying to log in and redirect accordingly
        if (employee.getAdmin()) {
            Admin admin = (Admin)
                    employeeService.findByUsername(principal.getName());
            model.addAttribute("admin", admin);
            adminService.update(admin);
            return "adminPanel";
        }
        if (employee.getFirstLogin()) {
            model.addAttribute("username", employee.getUsername());
            model.addAttribute("password", "");
            model.addAttribute("repeatPassword", "");
            return "changePassword";
        }
        model.addAttribute("employee", employee);
        return "employeePanel";
    }

    /**
     * Handles the change in password and deletes the One Time Usage Password.
     *
     * @param model
     * @param password
     * @param repeatPassword
     * @return
     */
    @RequestMapping(value = "/changePassword",
            method = {RequestMethod.POST})
    public String changePassword(Model model, @RequestParam String[] password,
                                 @RequestParam String repeatPassword) {
        Employee employee = employeeService.findByUsername(password[0]);
        if (password[1].equals(repeatPassword)) {
            otuPasswordService.useOTUPassword(password[0]);
            if (employee.getIsExpired()) {
                model.addAttribute("error", "One time use password expired. " +
                        "Contact an Administrator for more Information.");
                return "/terminal";
            }
            employeeService.changePassword(employee, false, password[1]);
            model.addAttribute("employee", employee);
            return "employeePanel";
        } else {
            model.addAttribute("error", "Password doesn't equal repeated " +
                    "password. Please try again");
            return "changePassword";
        }
    }
}

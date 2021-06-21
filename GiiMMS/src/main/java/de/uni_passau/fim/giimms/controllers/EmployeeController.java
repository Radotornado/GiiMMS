package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EmployeeValidator employeeValidator;

    @GetMapping("/addEmployee")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "redirect:/";
        }

        model.addAttribute("employeeForm", new Employee());

        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String registration(@ModelAttribute("employeeForm") Employee employeeForm, BindingResult bindingResult) {

        employeeValidator.validate(employeeForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "addEmployee";
        }

        employeeService.save(employeeForm);

        securityService.autoLogin(employeeForm.getEmail(), employeeForm.getPassword());

        return "redirect:/employeeProfile";
    }

    @GetMapping("/terminal")
    public String login(Model model, String error, String logout) {

        if (securityService.isAuthenticated()) {
            return "redirect:/employeeProfile";
        }

        if (error != null)
            model.addAttribute("error", "Email or password is invalid.");

        if (logout != null)
            model.addAttribute("message", "Logged out successfully.");

        return "/terminal";
    }

    @GetMapping({"/employeeProfile", "/"})
    public String welcome(Model model) {
        return "employeeProfile";
    }
}


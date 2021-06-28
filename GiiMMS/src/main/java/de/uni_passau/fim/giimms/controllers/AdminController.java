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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private EmployeeValidator employeeValidator;

    @GetMapping("/addEmployee")
    public String registration(Model model) {
        if (securityService.isAuthenticated()) {
            return "addEmployee";
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

        securityService.autoLogin(employeeForm.getUsername(), employeeForm.getPassword());

        return "redirect:/employeePanel";
    }

    @GetMapping("/adminProfileView/{id:[1-9]+[0-9]*}")
    public String overviewEmployee(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", employeeService.findById(id));
        return "adminProfileView";
    }
}

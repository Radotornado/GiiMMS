package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.model.Coordinates;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.model.OTUPassword;
import de.uni_passau.fim.giimms.model.OfficialTerminal;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.OTUPasswordService;
import de.uni_passau.fim.giimms.services.OfficialTerminalService;
import de.uni_passau.fim.giimms.services.SecurityService;
import de.uni_passau.fim.giimms.util.EmployeeValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;


/**
 * Controls registration of new Employees and viewing profile of existing ones.
 */
@Controller
public class AdminController {

    @Autowired
    private EmployeeValidator employeeValidator;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private OTUPasswordService otuPasswordService;

    @Autowired
    private OfficialTerminalService officialTerminalService;

    /**
     * Handles returning of the page for adding employees.
     */
    @GetMapping("/addEmployee")
    public String registration(final Model model, Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        if (securityService.isAuthenticated() && employee.getAdmin()) {
            return "addEmployee";
        }
        model.addAttribute("employeeForm", new Employee());
        return "error";
    }

    /**
     * Handles registering a new Employee.
     */
    @PostMapping("/addEmployee")
    public String registration(@RequestParam("username") final String username,
                               @RequestParam("firstName") final String firstName,
                               @RequestParam("lastName") final String lastName,
                               @RequestParam("password") final String password,
                               @RequestParam("position") final String position) {
        // create it by the fields (already checked by js in the front-end)
        Employee employee = new Employee(username, password, firstName,
                lastName, position);
        // TODO: implement bindingResult and validator
        // employeeValidator.validate(employee, bindingResult);
        employeeService.save(employee);
        return "redirect:/";
    }

    /**
     * Handles returning of the page for adding employees.
     */
    @GetMapping("/officialTerminals")
    public String officialTerminal(final Model model, Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        if (securityService.isAuthenticated() && employee.getAdmin()) {
            Object[] officialTerminalList =
                    officialTerminalService.getAll().toArray();
            model.addAttribute("officialTerminals", officialTerminalList);
            return "officialTerminals";
        }
        return "error";
    }

    /**
     * Handles returning of the page for adding employees.
     */
    @GetMapping("/addTerminal")
    public String addTerminals(final Model model, Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        if (securityService.isAuthenticated() && employee.getAdmin()) {
            return "/addTerminal";
        }
        return "error";
    }

    /**
     * Handles returning of the page for adding employees.
     */
    @PostMapping("/addTerminal")
    public String offTerminalPost(final Model model, Principal principal,
                                  @RequestParam String branchName,
                                  @RequestParam String latitude,
                                  @RequestParam String longitude) {
        Employee employee = employeeService.findByUsername(principal.getName());
        try {
            if (securityService.isAuthenticated() && employee.getAdmin()) {

                Coordinates coordinates =
                        new Coordinates(Double.parseDouble(latitude),
                                Double.parseDouble(longitude));
                officialTerminalService.save(new OfficialTerminal(coordinates
                        , branchName));
                Object[] officialTerminalList =
                        officialTerminalService.getAll().toArray();
                model.addAttribute("officialTerminals", officialTerminalList);
                return "officialTerminals";
            }

        }catch(NumberFormatException exception){
            model.addAttribute("error", "The Longitude or Latitude was not proper." +
                    " Please try again." + exception.getLocalizedMessage());
        }
        return "/addTerminal";
    }

    /**
     * Handles viewing a specific employee's profile.
     */
    @GetMapping("/adminProfileView/{id:[1-9]+[0-9]*}")
    public String overviewEmployee(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", employeeService.findById(id));
        return "adminProfileView";
    }
}

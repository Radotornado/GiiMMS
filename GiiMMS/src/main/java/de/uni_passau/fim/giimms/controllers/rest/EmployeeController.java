package de.uni_passau.fim.giimms.controllers.rest;

import de.uni_passau.fim.giimms.repositories.EmployeeRepository;
import de.uni_passau.fim.giimms.util.Employee;
import de.uni_passau.fim.giimms.util.EmployeeNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private final EmployeeRepository employeeRepository;

    @PostMapping("/")
    public ModelAndView loginEmployee(@RequestBody Map<String, Object> map, Model model) {
        String username = (String) map.get("username");
        String password = (String) map.get("password");
        Employee employee = employeeRepository.findByUsername(username);
        if (password.equals(employee.getPassword())) {
            // if the password and name match
            model.addAttribute("employee", employee);
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("employeeProfile");
            return modelAndView;
        } else {
            // if the name matches, but the password not
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("index");
            return modelAndView;
        }
        //if (employee == null) {
             //if it is an admin or unknown
        //}
        // only if he is an employee

    }

    @GetMapping("/employees/{id}")
    public ModelAndView showProfile(@PathVariable("id") long id, Model model) {
        Employee employee = employeeRepository.findById(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Invalid id.");
        }
        model.addAttribute("employee", employee);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminView");
        return modelAndView;
    }

    @GetMapping("/adminPage")
    public ModelAndView showAll(Model model) {
        List<Employee> employees = (List<Employee>) employeeRepository.findAll();
        model.addAttribute("employees", employees);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage");
        return modelAndView;
    }

}

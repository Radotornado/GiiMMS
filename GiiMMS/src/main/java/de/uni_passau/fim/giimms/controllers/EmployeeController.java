package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.GiiMmsApplication;
import de.uni_passau.fim.giimms.model.Admin;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.JsonExporterService;
import de.uni_passau.fim.giimms.services.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private JsonExporterService jsonExporter;

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
            employeeService.update(admin);
            return "adminPanel";
        }
        model.addAttribute("employee", employee);
        return "employeePanel";
    }

    @GetMapping("/exportJSON/{id:[1-9]+[0-9]*}")
    public ResponseEntity<byte[]> exportJSON(@PathVariable("id") Long id) {
        Employee employee = employeeService.findById(id);
        String employeeJSON = jsonExporter.export(employee);
        byte[] employeeJSONBytes = employeeJSON.getBytes();
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + employee.getUsername() + ".json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(employeeJSONBytes.length)
                .body(employeeJSONBytes);
    }

    @PostMapping("/")
    public String handleForm(@RequestParam("details") String input, Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        employee.setData(input);
        Logger log = LoggerFactory.getLogger(GiiMmsApplication.class);
        // FIXME
        //employeeService.delete(employee.getUsername());
        //employeeService.save(employee);
        return "redirect:/";
    }
}


package de.uni_passau.fim.giimms.controllers;

import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.EmployeeService;
import de.uni_passau.fim.giimms.services.JsonExporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * Controls exporting and data addition of an Employee.
 */
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private JsonExporterService jsonExporter;

    /**
     * Returns a JSON file, containing all data for the current Employee.
     */
    @GetMapping("/exportJSON/{id:[1-9]+[0-9]*}")
    public ResponseEntity<byte[]> exportJSON(
            @PathVariable("id") final Long id) {
        // get the Employee by the id
        Employee employee = employeeService.findById(id);
        // export it as JSON
        String employeeJSON = jsonExporter.export(employee);
        // convert it to a byte array
        byte[] employeeJSONBytes = employeeJSON.getBytes();
        // return it as a response entity, naming the file with the username
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + employee.getUsername() + ".json")
                .contentType(MediaType.APPLICATION_JSON)
                .contentLength(employeeJSONBytes.length)
                .body(employeeJSONBytes);
    }

    /**
     * Returns a CSV file, containing all data for the current Employee.
     */
    @GetMapping("/exportCSV/{id:[1-9]+[0-9]*}")
    public void exportCSV(@PathVariable("id") final Long id,
                          final HttpServletResponse response)
            throws IOException {
        // get the employee by the id
        Employee employee = employeeService.findById(id);
        // create the header value
        String headerValue = "attachment; filename="
                + employee.getUsername() + ".csv";
        // set the header
        response.setHeader("Content-Disposition", headerValue);
        // write it as csv
        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                CsvPreference.STANDARD_PREFERENCE);
        // map column names to fields
        String[] csvHeader = {"Username", "First Name", "Last Name",
                "Position", "Coordinates", "Status", "Data"};
        String[] nameMapping = {"username", "firstName", "lastName",
                "position", "coordinates", "status", "data"};
        // write them and close the writer
        csvWriter.writeHeader(csvHeader);
        csvWriter.write(employee, nameMapping);
        csvWriter.close();
    }

    /**
     * Handles the Employee data (text about his work today).
     */
    @PostMapping("/")
    public String handleForm(@RequestParam("details") final String input,
                             final Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        employee.setData(input);
        // TODO:
        //employeeService.delete(employee.getUsername());
        //employeeService.save(employee);
        return "redirect:/";
    }

    /**
     * Handles redirect to changeUsername panel.
     */
    @GetMapping("/changeUsername")
    public String getChangeEmployee() {
        return "changeUsername";
    }

    /**
     * Handles changing the username.
     */
    @PostMapping("/changeUsername")
    public String changeEmployeeUsername(
            @RequestParam("newUsername") final String newUsername,
            final Principal principal) {
        Employee employee = employeeService.findByUsername(principal.getName());
        employeeService.changeUsername(employee, newUsername);
        SecurityContextHolder.getContext().setAuthentication(null);
        return "/terminal";
    }
}


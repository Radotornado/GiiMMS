package de.uni_passau.fim.giimms.services;

import de.uni_passau.fim.giimms.model.Employee;

/**
 * The json exporter service is responsible for exporting an Employee
 * into a valid JSON object.
 */
public interface JsonExporterService {

    /**
     * Creates a new Gson object and converts the employee JSON string to
     * a valid JSON.
     */
    String export(Employee employee);
}

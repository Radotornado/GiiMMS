package de.uni_passau.fim.giimms.services.impl;

import com.google.gson.Gson;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.JsonExporterService;
import org.springframework.stereotype.Service;

/**
 * Implementation of the JsonExporterService. Main logic for converting
 * an Employee to JSON string.
 */
@Service
public class JsonExporterServiceImpl implements JsonExporterService {

    /**
     * {@inheritDoc}
     */
    @Override
    public String export(Employee employee) {
        return new Gson().toJson(employee.toJson());
    }
}

package de.uni_passau.fim.giimms.services.impl;

import com.google.gson.Gson;
import de.uni_passau.fim.giimms.model.Employee;
import de.uni_passau.fim.giimms.services.JsonExporterService;
import org.springframework.stereotype.Service;

@Service
public class JsonExporterServiceImpl implements JsonExporterService {
    @Override
    public String export(Employee employee) {
        Gson gson = new Gson();
        return gson.toJson(employee.toJson());
    }
}

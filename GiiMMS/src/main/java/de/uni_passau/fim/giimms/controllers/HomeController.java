package de.uni_passau.fim.giimms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String getHome() {
        return "index";
    }

    // temporary just for testing
    @RequestMapping("/adminPage")
    public String getAdminPage() {
        return "adminPage";
    }

    // temporary just for testing
    @RequestMapping("/addEmployee")
    public String getAddEmployeePage() { return "addEmployee"; }


}

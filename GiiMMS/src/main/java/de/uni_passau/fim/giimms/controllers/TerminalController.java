package de.uni_passau.fim.giimms.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TerminalController {

    @RequestMapping("/")
    public String getHome() {
        return "index";
    }

    @RequestMapping("/addEmployee")
    public String getAdd() {
        return "addEmployee";
    }

}

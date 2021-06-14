package de.uni_passau.fim.giimms.controllers.advices;

import de.uni_passau.fim.giimms.util.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String eventNotFoundHandler(EmployeeNotFoundException ex,
                                       Model model) {
        model.addAttribute("errMsg", ex.getMessage());
        return "error";
    }

}

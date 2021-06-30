package de.uni_passau.fim.giimms.controllers.advices;

import de.uni_passau.fim.giimms.util.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Go to error page if page is not found.
 */
@ControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String eventNotFoundHandler(final EmployeeNotFoundException ex,
                                       final Model model) {
        model.addAttribute("errMsg", ex.getMessage());
        return "error";
    }

}

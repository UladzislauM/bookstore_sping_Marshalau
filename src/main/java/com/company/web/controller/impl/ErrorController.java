package com.company.web.controller.impl;

import com.company.service.exception.AppException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@Controller
@ControllerAdvice
@RequestMapping("/error")
public class ErrorController {
    @GetMapping
    public String error(){
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String applicationError(AppException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String applicationError2(AppException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String applicationError(AccountNotFoundException e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}

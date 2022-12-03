package com.github.renamrgb.lojavirtual.api.exceptions;

import com.github.renamrgb.lojavirtual.application.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final String NOT_FOUND_CODE = "404";
    private static final String UNPROCESSABLE_ENTITY_CODE = "422";

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public StandardError resourceNotFound(ResourceNotFoundException e) {
        StandardError err = new StandardError();
        err.setCode(NOT_FOUND_CODE);
        err.setMessage(e.getMessage());
        return err;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ValidatorError validation(MethodArgumentNotValidException e) {
        ValidatorError err = new ValidatorError();
        err.setCode(UNPROCESSABLE_ENTITY_CODE);
        err.setMessage("Request informada esta fora das especificações.");

        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            err.addErro(f.getField(), f.getDefaultMessage());
        }

        return err;
    }
}

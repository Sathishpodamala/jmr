package com.bank.jmr.exceptionhandlers;

import com.bank.jmr.exception.CustomerCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String>invalidFieldsInBean(MethodArgumentNotValidException exception)
    {
        return exception.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(err->err.getField(),err->err.getDefaultMessage()));
    }

    @ExceptionHandler(CustomerCustomException.class)
    public String customerCustomExceptionHandler(CustomerCustomException exception)
    {
        return exception.getMessage();
    }

}

package ru.tkachenko.ecare.util;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(Exception.class)
    public String handlerAllExceptions(HttpServletRequest request, Exception x) {
        //request and e - for logger
        return "errors/500";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handlerSearchClientException(HttpServletRequest request, Exception e) {
        return "errors/500_non";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handlerMatcherContractException(HttpServletRequest request, Exception x) {
        return "errors/500_match";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handlerAccessContractException(HttpServletRequest request, Exception x) {
        return "errors/403";
    }
}
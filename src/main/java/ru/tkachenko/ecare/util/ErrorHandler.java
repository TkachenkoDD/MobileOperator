package ru.tkachenko.ecare.util;

import org.apache.log4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {

    private final Logger logger = Logger.getLogger(ErrorHandler.class);

    @ExceptionHandler(Exception.class)
    public String handlerAllExceptions(HttpServletRequest request, Exception e) {
        logger.error(request.getRequestURI() + " " + e.toString());
        return "errors/500";
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handlerSearchClientException(HttpServletRequest request, Exception e) {
        logger.error(request.getRequestURI() + " Trying to find a non-existent number. " + e.toString());
        return "errors/500_non";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handlerMatcherContractException(HttpServletRequest request, Exception x) {
        logger.error(request.getRequestURI() + " Attempt to create an existing number. " + x.toString());
        return "errors/500_match";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handlerAccessContractException(HttpServletRequest request, Exception x) {
        logger.error(request.getRequestURI() + " Trying to leave the restricted area. " + x.toString());
        return "errors/403";
    }
}
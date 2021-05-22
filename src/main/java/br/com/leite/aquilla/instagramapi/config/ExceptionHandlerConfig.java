package br.com.leite.aquilla.instagramapi.config;

import br.com.leite.aquilla.instagramapi.exception.BusinessException;
import br.com.leite.aquilla.instagramapi.model.StandardError;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerConfig {

    // Spring and Java Exceptions
    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<StandardError> runtimeException(RuntimeException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var standardError = StandardError.create(status.value(), request.getRequestURI());
        standardError.getErrors().add(e.getMessage());

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var standardError = StandardError.create(status.value(), request.getRequestURI());

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String message = fieldError.getField() + " " + fieldError.getDefaultMessage();
            standardError.getErrors().add(message);
        }

        return ResponseEntity.status(status).body(standardError);
    }

    @ExceptionHandler(InvalidFormatException.class)
    protected ResponseEntity<StandardError> invalidFormatException(InvalidFormatException e, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var standardError = StandardError.create(status.value(), request.getRequestURI());
        standardError.getErrors().add(e.getOriginalMessage());

        return ResponseEntity.status(status).body(standardError);
    }

    // Custom project Exceptions
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<StandardError> businessException(BusinessException e, HttpServletRequest request) {
        var standardError = StandardError.create(e.getHttpStatus().value(), request.getRequestURI());

        for (String x : e.getErrors()) {
            standardError.getErrors().add(x);
        }

        return ResponseEntity.status(e.getHttpStatus().value()).body(standardError);
    }
}

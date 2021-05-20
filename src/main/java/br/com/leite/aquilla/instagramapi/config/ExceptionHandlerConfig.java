package br.com.leite.aquilla.instagramapi.config;

import br.com.leite.aquilla.instagramapi.exception.NotFoundException;
import br.com.leite.aquilla.instagramapi.model.StandardError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardError> NotFoundException(NotFoundException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = StandardError.builder()
                .timestamp(Instant.now())
                .status(status.value())
                .path(request.getRequestURI())
                .message(e.getMessage())
                .build();
        LOGGER.info(standardError.toString());
        return ResponseEntity.status(status).body(standardError);
    }
}

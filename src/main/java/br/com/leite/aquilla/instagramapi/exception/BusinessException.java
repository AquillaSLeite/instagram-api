package br.com.leite.aquilla.instagramapi.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public abstract class BusinessException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final List<String> errors;

    protected BusinessException(final HttpStatus httpStatus, final List<String> errors) {
        this.httpStatus = httpStatus;
        this.errors = errors;
    }
}
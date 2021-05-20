package br.com.leite.aquilla.instagramapi.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ConflictException extends BusinessException {

    public ConflictException(final List<String> errors) {
        super(HttpStatus.CONFLICT, errors);
    }
}

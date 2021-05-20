package br.com.leite.aquilla.instagramapi.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class NotFoundException extends BusinessException {

    public NotFoundException(final String message) {
        super(HttpStatus.NOT_FOUND, List.of(message));
    }
}

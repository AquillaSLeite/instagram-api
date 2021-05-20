package br.com.leite.aquilla.instagramapi.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class BadRequestException extends BusinessException {

    public BadRequestException(final String message) {
        super(HttpStatus.BAD_REQUEST, List.of(message));
    }
}

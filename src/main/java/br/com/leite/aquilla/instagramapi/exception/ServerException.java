package br.com.leite.aquilla.instagramapi.exception;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ServerException extends BusinessException {
    public ServerException(final String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, List.of(message));
    }
}

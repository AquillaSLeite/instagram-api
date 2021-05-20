package br.com.leite.aquilla.instagramapi.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public interface UtilsController {
    static URI generatedUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }
}

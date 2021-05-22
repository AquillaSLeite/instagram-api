package br.com.leite.aquilla.instagramapi.controller;

import br.com.leite.aquilla.instagramapi.model.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CommentController {

    @PostMapping
    ResponseEntity<Void> addComment(@PathVariable Long id, @RequestBody @Valid CommentDto dto);

    @DeleteMapping
    ResponseEntity<Void> removeComment(@PathVariable Long id, @RequestBody @Valid CommentDto dto);
}

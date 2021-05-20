package br.com.leite.aquilla.instagramapi.controller;

import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public interface PostController {

    @PostMapping
    PostDto save(@RequestParam("files") MultipartFile[] files,
                 @RequestParam("describe") String describe,
                 @RequestParam("author") Long author);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);
}

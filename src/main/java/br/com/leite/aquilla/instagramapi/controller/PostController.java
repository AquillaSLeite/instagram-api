package br.com.leite.aquilla.instagramapi.controller;

import br.com.leite.aquilla.instagramapi.model.dto.PostCommentsDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostController {

    @PostMapping
    PostDto save(@RequestParam("files") MultipartFile[] files,
                 @RequestParam("text") String text,
                 @RequestParam("user") Long author);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping("{id}")
    ResponseEntity<PostDto> show(@PathVariable Long id);

    @GetMapping("{id}/comments")
    ResponseEntity<List<PostCommentsDto>> comments(@PathVariable Long id);
}

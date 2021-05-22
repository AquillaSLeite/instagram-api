package br.com.leite.aquilla.instagramapi.controller.impl;

import br.com.leite.aquilla.instagramapi.controller.CommentController;
import br.com.leite.aquilla.instagramapi.model.dto.CommentDto;
import br.com.leite.aquilla.instagramapi.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("comments")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentControllerImpl implements CommentController {

    CommentService commentService;

    @Override
    public ResponseEntity<Void> addComment(Long id, @Valid CommentDto dto) {
        commentService.addComment(dto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> removeComment(Long id, @Valid CommentDto dto) {
        commentService.removeComment(dto);
        return ResponseEntity.ok().build();
    }
}

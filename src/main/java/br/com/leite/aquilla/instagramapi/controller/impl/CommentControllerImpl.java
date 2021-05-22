package br.com.leite.aquilla.instagramapi.controller.impl;

import br.com.leite.aquilla.instagramapi.controller.CommentController;
import br.com.leite.aquilla.instagramapi.model.dto.CommentDto;
import br.com.leite.aquilla.instagramapi.service.CommentService;
import br.com.leite.aquilla.instagramapi.util.UtilController;
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
    public ResponseEntity<CommentDto> save(@Valid CommentDto dto) {
        var comment = commentService.save(dto);
        return ResponseEntity.created(UtilController.generatedUri(comment.getId())).body(comment);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

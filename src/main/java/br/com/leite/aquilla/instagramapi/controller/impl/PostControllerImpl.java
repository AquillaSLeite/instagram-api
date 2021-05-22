package br.com.leite.aquilla.instagramapi.controller.impl;

import br.com.leite.aquilla.instagramapi.controller.PostController;
import br.com.leite.aquilla.instagramapi.model.dto.PostCommentsDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import br.com.leite.aquilla.instagramapi.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("posts")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PostControllerImpl implements PostController {

    PostService postService;

    @Override
    public PostDto save(final MultipartFile[] files, final String text, final Long user) {
        var postDto = PostDto.builder()
                .text(text)
                .user(user)
                .build();
        return postService.save(postDto, files);
    }

    @Override
    public ResponseEntity<Void> delete(final Long id) {
        postService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PostDto> show(final Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @Override
    public ResponseEntity<List<PostCommentsDto>> comments(final Long id) {
        return ResponseEntity.ok(postService.comments(id));
    }
}

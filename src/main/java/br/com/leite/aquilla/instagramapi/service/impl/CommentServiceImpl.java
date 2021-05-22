package br.com.leite.aquilla.instagramapi.service.impl;

import br.com.leite.aquilla.instagramapi.entity.Comment;
import br.com.leite.aquilla.instagramapi.exception.NotFoundException;
import br.com.leite.aquilla.instagramapi.mapper.CommentMapper;
import br.com.leite.aquilla.instagramapi.model.dto.CommentDto;
import br.com.leite.aquilla.instagramapi.repository.CommentRepository;
import br.com.leite.aquilla.instagramapi.service.CommentService;
import br.com.leite.aquilla.instagramapi.service.PostService;
import br.com.leite.aquilla.instagramapi.service.UserService;
import br.com.leite.aquilla.instagramapi.util.UtilConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    CommentMapper commentMapper;
    UserService userService;
    PostService postService;

    @Override
    public CommentDto save(final CommentDto dto) {
        var comment = commentMapper.toEntity(dto);
        var user = userService.userById(dto.getUser());
        var post = postService.postById(dto.getPost());

        comment.setCreatedAt(LocalDateTime.now());
        comment.setPost(post);
        comment.setUser(user);
        return commentMapper.toDTO(commentRepository.save(comment));
    }

    @Override
    public void delete(final Long id) {
        commentRepository.delete(commentById(id));
    }

    public Comment commentById(final Long id) {
        return commentRepository.findById(id).orElseThrow(() -> new NotFoundException(UtilConstants.entityNotFoundReplace(Comment.class.getSimpleName(), id)));
    }
}

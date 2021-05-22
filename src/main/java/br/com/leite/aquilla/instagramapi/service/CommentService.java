package br.com.leite.aquilla.instagramapi.service;

import br.com.leite.aquilla.instagramapi.model.dto.CommentDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Validated
public interface CommentService {

    @Transactional
    CommentDto save(@NotNull CommentDto dto);

    @Transactional
    void delete(@NotNull Long id);

    @Transactional
    void addComment(@NotNull CommentDto dto);

    @Transactional
    void removeComment(@NotNull CommentDto dto);
}

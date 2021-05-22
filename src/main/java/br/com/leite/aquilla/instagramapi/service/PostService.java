package br.com.leite.aquilla.instagramapi.service;

import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.model.dto.PostCommentsDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface PostService {

    @Transactional
    PostDto save(@NotNull PostDto dto, @NotNull MultipartFile[] files);

    @Transactional
    void delete(@NotNull Long id);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    PostDto findById(@NotNull Long id);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<PostCommentsDto> comments(@NotNull Long id);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Post postById(@NotNull Long id);
}

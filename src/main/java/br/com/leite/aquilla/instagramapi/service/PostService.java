package br.com.leite.aquilla.instagramapi.service;

import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Validated
public interface PostService {

    @Transactional
    PostDto save(@NotNull PostDto dto, @NotNull MultipartFile[] files);

    @Transactional
    void delete(@NotNull Long id);
}

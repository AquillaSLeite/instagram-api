package br.com.leite.aquilla.instagramapi.service;

import br.com.leite.aquilla.instagramapi.entity.File;
import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.model.dto.FileDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Validated
public interface FileService {

    @Transactional
    List<FileDto> save(@NotNull MultipartFile[] files, @NotNull Post post);

    @Transactional
    void delete(@NotNull Set<File> files);
}

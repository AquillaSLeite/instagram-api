package br.com.leite.aquilla.instagramapi.service;

import br.com.leite.aquilla.instagramapi.entity.File;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Validated
public interface AwsService {

    @Transactional
    String saveAwsS3(@NotNull MultipartFile file, @NotNull String name) throws IOException;

    @Transactional
    void deleteAwsS3(@NotNull List<File> files);
}

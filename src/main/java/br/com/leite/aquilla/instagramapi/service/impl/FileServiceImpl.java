package br.com.leite.aquilla.instagramapi.service.impl;

import br.com.leite.aquilla.instagramapi.entity.File;
import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.exception.ServerException;
import br.com.leite.aquilla.instagramapi.mapper.FileMapper;
import br.com.leite.aquilla.instagramapi.model.dto.FileDto;
import br.com.leite.aquilla.instagramapi.repository.FileRepository;
import br.com.leite.aquilla.instagramapi.service.AwsService;
import br.com.leite.aquilla.instagramapi.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {

    @Value("${file.upload-dir}")
    private final Path pathStorageLocation = Paths.get("{1}");
    private final FileRepository fileRepository;
    private final FileMapper fileMapper;
    private final AwsService awsService;

    @Override
    public List<FileDto> save(final MultipartFile[] files, final Post post) {
        final var fileList = new ArrayList<File>();

        Arrays.stream(files).forEach(file -> {
            var originalName = file.getOriginalFilename();
            var extensionPoint = originalName.lastIndexOf(".");
            var name = UUID.randomUUID().toString() + originalName.substring(extensionPoint);

            try {
                fileList.add(File.builder()
                        .name(name)
                        .path(awsService.saveAwsS3(file, name))
                        .post(post)
                        .build());
            } catch (IOException e) {
                awsService.deleteAwsS3(fileList);
                throw new ServerException("Error trying to write the file to the server");
            }
        });

        return fileRepository.saveAll(fileList).stream()
                .map(fileMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(final Set<File> files) {
        awsService.deleteAwsS3(new ArrayList<>(files));
    }
}

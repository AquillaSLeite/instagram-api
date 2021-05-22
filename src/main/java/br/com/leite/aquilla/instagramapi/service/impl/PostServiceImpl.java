package br.com.leite.aquilla.instagramapi.service.impl;

import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.exception.NotFoundException;
import br.com.leite.aquilla.instagramapi.mapper.PostMapper;
import br.com.leite.aquilla.instagramapi.model.dto.FileDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostCommentsDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import br.com.leite.aquilla.instagramapi.repository.PostRepository;
import br.com.leite.aquilla.instagramapi.service.FileService;
import br.com.leite.aquilla.instagramapi.service.PostService;
import br.com.leite.aquilla.instagramapi.service.UserService;
import br.com.leite.aquilla.instagramapi.util.UtilConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final FileService fileService;
    private final PostMapper postMapper;

    @Override
    public PostDto save(final PostDto dto, final MultipartFile[] files) {
        var post = postMapper.toEntity(dto);
        post.setUser(userService.userById(dto.getUser()));
        post.setCreatedAt(LocalDateTime.now());

        var postDb = postRepository.save(post);
        var postDbDto = postMapper.toDTO(postDb);

        for (FileDto file : fileService.save(files, postDb)) {
            postDbDto.getFiles().add(file);
        }
        return postDbDto;
    }

    @Override
    public void delete(final Long id) {
        var post = postById(id);
        fileService.delete(post.getFiles());
        postRepository.delete(post);
    }

    @Override
    public PostDto findById(@NotNull Long id) {
        return postMapper.toDTO(postById(id));
    }

    @Override
    public List<PostCommentsDto> comments(@NotNull Long id) {
        return postById(id).getComments()
                .stream()
                .map(postMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Post postById(final Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException(UtilConstants.entityNotFoundReplace(Post.class.getSimpleName(), id)));
    }
}

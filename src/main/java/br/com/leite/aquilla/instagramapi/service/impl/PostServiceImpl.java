package br.com.leite.aquilla.instagramapi.service.impl;

import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.exception.NotFoundException;
import br.com.leite.aquilla.instagramapi.mapper.PostMapper;
import br.com.leite.aquilla.instagramapi.model.dto.FileDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import br.com.leite.aquilla.instagramapi.repository.PostRepository;
import br.com.leite.aquilla.instagramapi.service.FileService;
import br.com.leite.aquilla.instagramapi.service.PostService;
import br.com.leite.aquilla.instagramapi.service.UserService;
import br.com.leite.aquilla.instagramapi.util.UtilsConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final FileService fileService;
    private final PostMapper postMapper;

    @Override
    public PostDto save(final PostDto dto, final MultipartFile[] files) {
        var post = this.postMapper.toEntity(dto);
        post.setAuthor(userService.userById(dto.getAuthor()));

        var postDb = postRepository.save(post);
        var postDto = postMapper.toDTO(postDb);

        for (FileDto file : fileService.save(files, postDb)) {
            postDto.getFiles().add(file);
        }
        return postDto;
    }

    @Override
    public void delete(final Long id) {
        var post = postById(id);
        fileService.delete(post.getFiles());
        postRepository.delete(post);
    }

    private Post postById(final Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException(UtilsConstants.entityNotFoundReplace(Post.class.getSimpleName(), id)));
    }
}

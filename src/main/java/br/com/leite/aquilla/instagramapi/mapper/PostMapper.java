package br.com.leite.aquilla.instagramapi.mapper;

import br.com.leite.aquilla.instagramapi.entity.Comment;
import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.model.dto.PostCommentsDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "user", ignore = true)
    Post toEntity(PostDto dto);

    @Mapping(source = "user.id", target = "user")
    PostDto toDTO(Post obj);

    @Mapping(source = "user.id", target = "user")
    PostCommentsDto toDTO(Comment obj);
}

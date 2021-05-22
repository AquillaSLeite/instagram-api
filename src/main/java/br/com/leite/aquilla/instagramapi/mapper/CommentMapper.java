package br.com.leite.aquilla.instagramapi.mapper;

import br.com.leite.aquilla.instagramapi.entity.Comment;
import br.com.leite.aquilla.instagramapi.model.dto.CommentDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "post", ignore = true)
    Comment toEntity(CommentDto dto);

    @Mapping(source = "user.id", target = "user")
    @Mapping(source = "post.id", target = "post")
    CommentDto toDTO(Comment obj);
}

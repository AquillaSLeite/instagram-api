package br.com.leite.aquilla.instagramapi.mapper;

import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "author", ignore = true)
    Post toEntity(PostDto dto);

    @Mapping(source = "author.id", target = "author")
    PostDto toDTO(Post obj);
}

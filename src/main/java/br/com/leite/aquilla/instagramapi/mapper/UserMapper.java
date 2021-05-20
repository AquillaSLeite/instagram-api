package br.com.leite.aquilla.instagramapi.mapper;

import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.entity.User;
import br.com.leite.aquilla.instagramapi.model.dto.UserDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserPostsDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDto dto);

    UserDto toDTO(User obj);

    UserPostsDto toDTO(Post obj);
}

package br.com.leite.aquilla.instagramapi.service;

import br.com.leite.aquilla.instagramapi.entity.User;
import br.com.leite.aquilla.instagramapi.model.dto.FollowDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserPostsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface UserService {

    @Transactional
    UserDto save(@NotNull UserDto dto);

    @Transactional
    UserDto update(@NotNull UserDto dto, @NotNull Long id);

    @Transactional
    void delete(@NotNull Long id);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    Page<UserDto> findAll(@NotNull Pageable page);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    UserDto findById(@NotNull Long id);

    @Transactional
    List<UserDto> following(@NotNull Long id);

    @Transactional
    void addFollowing(@NotNull Long id, @NotNull FollowDto dto);

    @Transactional
    void removeFollowing(@NotNull Long id, @NotNull Long following);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<UserDto> followers(@NotNull Long id);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    List<UserPostsDto> posts(@NotNull Long id);

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    User userById(@NotNull Long id);
}

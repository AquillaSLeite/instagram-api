package br.com.leite.aquilla.instagramapi.service.impl;

import br.com.leite.aquilla.instagramapi.entity.User;
import br.com.leite.aquilla.instagramapi.exception.BadRequestException;
import br.com.leite.aquilla.instagramapi.exception.ConflictException;
import br.com.leite.aquilla.instagramapi.exception.NotFoundException;
import br.com.leite.aquilla.instagramapi.mapper.UserMapper;
import br.com.leite.aquilla.instagramapi.model.dto.FollowDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserPostsDto;
import br.com.leite.aquilla.instagramapi.repository.UserRepository;
import br.com.leite.aquilla.instagramapi.service.UserService;
import br.com.leite.aquilla.instagramapi.util.UtilConstants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto save(final UserDto dto) {
        var user = userMapper.toEntity(dto);
        user.setId(null);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        userRulesValidation(user);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDto update(final UserDto dto, final Long id) {
        var userDb = userById(id);

        var user = userMapper.toEntity(dto);
        user.setId(userDb.getId());
        user.setCreatedAt(userDb.getCreatedAt());
        user.setUpdatedAt(LocalDateTime.now());
        userRulesValidation(user);

        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public void delete(final Long id) {
        userRepository.delete(userById(id));
    }

    public Page<UserDto> findAll(final Pageable page) {
        List<UserDto> userDtoList = userRepository.findAll(page).stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(userDtoList, page, page.getPageSize());
    }

    @Override
    public UserDto findById(final Long id) {
        return userMapper.toDTO(userById(id));
    }

    @Override
    public List<UserDto> following(final Long id) {
        return userById(id).getFollowing()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addFollowing(final Long id, final FollowDto dto) {
        if (id.equals(dto.getUser()))
            throw new BadRequestException(UtilConstants.IDENTITY_FOLLOW_USER);

        var user = userById(id);
        var userFollowing = userById(dto.getUser());

        // TODO: experimentar outra forma de inserir um novo seguidor sem instanciar todos as pessoas que o usuário está seguindo
        user.getFollowing().add(userFollowing);
        userRepository.save(user);
    }

    @Override
    public void removeFollowing(final Long id, final FollowDto dto) {
        var user = userById(id);
        var userFollowing = userById(dto.getUser());

        user.getFollowing().remove(userFollowing);
        userRepository.save(user);
    }

    @Override
    public List<UserDto> followers(final Long id) {
        return userById(id).getFollowers()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserPostsDto> posts(final Long id) {
        return userById(id).getPosts()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public User userById(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(UtilConstants.entityNotFoundReplace(User.class.getSimpleName(), id)));
    }

    private void userRulesValidation(final User user) {
        var errors = new ArrayList<String>();

        if (null == user.getId()) {
            if (userRepository.existsByUsername(user.getUsername()))
                errors.add(UtilConstants.USERNAME_ALREADY_EXISTS);
            if (userRepository.existsByEmail(user.getEmail()))
                errors.add(UtilConstants.EMAIL_ALREADY_EXISTS);
        } else {
            if (userRepository.existsByUsernameAndIdNot(user.getUsername(), user.getId()))
                errors.add(UtilConstants.USERNAME_ALREADY_EXISTS);
            if (userRepository.existsByEmailAndIdNot(user.getEmail(), user.getId()))
                errors.add(UtilConstants.EMAIL_ALREADY_EXISTS);
        }

        if (!errors.isEmpty())
            throw new ConflictException(errors);
    }
}

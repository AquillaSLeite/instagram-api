package br.com.leite.aquilla.instagramapi.controller.impl;

import br.com.leite.aquilla.instagramapi.controller.UserController;
import br.com.leite.aquilla.instagramapi.model.dto.FollowDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserPostsDto;
import br.com.leite.aquilla.instagramapi.service.UserService;
import br.com.leite.aquilla.instagramapi.util.UtilController;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserControllerImpl implements UserController {

    private final UserService userService;

    public ResponseEntity<UserDto> save(final UserDto dto) {
        var userDto = userService.save(dto);
        return ResponseEntity.created(UtilController.generatedUri(userDto.getId())).body(userDto);
    }

    public ResponseEntity<Void> addFollowing(final Long id, final FollowDto dto) {
        userService.addFollowing(id, dto);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserDto> update(final Long id, final UserDto dto) {
        return ResponseEntity.ok(userService.update(dto, id));
    }

    public ResponseEntity<Void> delete(final Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> removeFollowing(final Long id, final Long following) {
        userService.removeFollowing(id, following);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<UserDto>> index(final Pageable page) {
        return ResponseEntity.ok(userService.findAll(page));
    }

    public ResponseEntity<UserDto> show(final Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    public ResponseEntity<List<UserDto>> following(final Long id) {
        return ResponseEntity.ok(userService.following(id));
    }

    public ResponseEntity<List<UserDto>> followers(final Long id) {
        return ResponseEntity.ok(userService.followers(id));
    }

    public ResponseEntity<List<UserPostsDto>> posts(final Long id) {
        return ResponseEntity.ok(userService.posts(id));
    }
}

package br.com.leite.aquilla.instagramapi.controller;

import br.com.leite.aquilla.instagramapi.model.dto.FollowDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserDto;
import br.com.leite.aquilla.instagramapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto dto) {
        var userDto = userService.save(dto);
        return ResponseEntity.created(UtilController.generatedUri(userDto.getId())).body(userDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserDto dto) {
        return ResponseEntity.ok(userService.update(dto, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<UserDto>> index(Pageable page) {
        return ResponseEntity.ok(userService.findAll(page));
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> show(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("{id}/following")
    public ResponseEntity<List<UserDto>> following(@PathVariable Long id) {
        return ResponseEntity.ok(userService.following(id));
    }

    @PatchMapping("{id}/following/add")
    public ResponseEntity<UserDto> addFollowing(@PathVariable Long id, @RequestBody @Valid FollowDto dto) {
        return ResponseEntity.ok(userService.addFollowing(id, dto));
    }

    @PatchMapping("{id}/following/remove")
    public ResponseEntity<Void> removeFollowing(@PathVariable Long id, @RequestBody @Valid FollowDto dto) {
        userService.removeFollowing(id, dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}/followers")
    public ResponseEntity<List<UserDto>> followers(@PathVariable Long id) {
        return ResponseEntity.ok(userService.followers(id));
    }

    @GetMapping("{id}/posts")
    public ResponseEntity<List<PostDto>> posts(@PathVariable Long id) {
        return ResponseEntity.ok(userService.posts(id));
    }
}

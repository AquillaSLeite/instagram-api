package br.com.leite.aquilla.instagramapi.controller;

import br.com.leite.aquilla.instagramapi.model.dto.FollowDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserDto;
import br.com.leite.aquilla.instagramapi.model.dto.UserPostsDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public interface UserController {

    @PostMapping
    ResponseEntity<UserDto> save(@RequestBody @Valid UserDto dto);

    @PutMapping("{id}")
    ResponseEntity<UserDto> update(@PathVariable Long id, @RequestBody @Valid UserDto dto);

    @DeleteMapping("{id}")
    ResponseEntity<Void> delete(@PathVariable Long id);

    @GetMapping
    ResponseEntity<Page<UserDto>> index(@Valid Pageable page);

    @GetMapping("{id}")
    ResponseEntity<UserDto> show(@PathVariable Long id);

    @GetMapping("{id}/following")
    ResponseEntity<List<UserDto>> following(@PathVariable Long id);

    @PostMapping("{id}/following/add")
    ResponseEntity<UserDto> addFollowing(@PathVariable Long id, @RequestBody @Valid FollowDto dto);

    @PostMapping("{id}/following/remove")
    ResponseEntity<Void> removeFollowing(@PathVariable Long id, @RequestBody @Valid FollowDto dto);

    @GetMapping("{id}/followers")
    ResponseEntity<List<UserDto>> followers(@PathVariable Long id);

    @GetMapping("{id}/posts")
    ResponseEntity<List<UserPostsDto>> posts(@PathVariable Long id);
}

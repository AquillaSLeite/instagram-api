package br.com.leite.aquilla.instagramapi.service;

import br.com.leite.aquilla.instagramapi.entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findById(Long id);
}

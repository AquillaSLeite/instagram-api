package br.com.leite.aquilla.instagramapi.service.impl;

import br.com.leite.aquilla.instagramapi.entity.User;
import br.com.leite.aquilla.instagramapi.exception.NotFoundException;
import br.com.leite.aquilla.instagramapi.repository.UserRepository;
import br.com.leite.aquilla.instagramapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user with id " + id + " not found"));
    }
}

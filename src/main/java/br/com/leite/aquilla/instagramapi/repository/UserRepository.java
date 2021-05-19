package br.com.leite.aquilla.instagramapi.repository;

import br.com.leite.aquilla.instagramapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package br.com.leite.aquilla.instagramapi.repository;

import br.com.leite.aquilla.instagramapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}

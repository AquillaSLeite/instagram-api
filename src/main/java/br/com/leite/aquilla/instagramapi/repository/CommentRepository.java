package br.com.leite.aquilla.instagramapi.repository;

import br.com.leite.aquilla.instagramapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}

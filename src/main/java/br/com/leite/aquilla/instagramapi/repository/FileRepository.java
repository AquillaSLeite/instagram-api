package br.com.leite.aquilla.instagramapi.repository;

import br.com.leite.aquilla.instagramapi.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {

}

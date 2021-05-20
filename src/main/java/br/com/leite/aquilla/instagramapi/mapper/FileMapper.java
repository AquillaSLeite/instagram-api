package br.com.leite.aquilla.instagramapi.mapper;

import br.com.leite.aquilla.instagramapi.entity.File;
import br.com.leite.aquilla.instagramapi.entity.Post;
import br.com.leite.aquilla.instagramapi.model.dto.FileDto;
import br.com.leite.aquilla.instagramapi.model.dto.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File toEntity(FileDto dto);

    FileDto toDTO(File obj);
}

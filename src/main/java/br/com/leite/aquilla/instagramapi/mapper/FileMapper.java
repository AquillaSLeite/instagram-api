package br.com.leite.aquilla.instagramapi.mapper;

import br.com.leite.aquilla.instagramapi.entity.File;
import br.com.leite.aquilla.instagramapi.model.dto.FileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FileMapper {

    File toEntity(FileDto dto);

    FileDto toDTO(File obj);
}

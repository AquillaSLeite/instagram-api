package br.com.leite.aquilla.instagramapi.model.dto;

import br.com.leite.aquilla.instagramapi.entity.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPostsDto {
    private Long id;
    private String describe;
    private List<FileDto> files;
}

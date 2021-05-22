package br.com.leite.aquilla.instagramapi.model.dto;

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
    private String text;
    private List<FileDto> files;
}

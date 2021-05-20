package br.com.leite.aquilla.instagramapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class StandardError {
    private static final long serialVersionUID = 1L;

    private Instant timestamp;
    private Integer status;
    private String path;
    private final List<String> errors = new ArrayList<>();

    public static StandardError create(Integer status, String uri) {
        return StandardError.builder()
                .timestamp(Instant.now())
                .status(status)
                .path(uri)
                .build();
    }
}

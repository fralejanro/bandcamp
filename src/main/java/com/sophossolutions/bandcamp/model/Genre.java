package com.sophossolutions.bandcamp.model;

import com.sophossolutions.bandcamp.dto.GenreDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class Genre {
    @Id
    private Integer id;
    private String name;

    public GenreDTO toDTO(Genre genre) {
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .build();
    }
}

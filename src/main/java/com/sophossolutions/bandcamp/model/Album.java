package com.sophossolutions.bandcamp.model;

import com.sophossolutions.bandcamp.dto.AlbumDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
public class Album {
    @Id
    private Integer id;
    private String name;
    private Integer type;
    private LocalDate releaseDate;

    public AlbumDTO toDTO(Album album) {
        return AlbumDTO.builder()
                .id(album.getId())
                .name(album.getName())
                .releaseDate(album.releaseDate)
                .build();
    }
}

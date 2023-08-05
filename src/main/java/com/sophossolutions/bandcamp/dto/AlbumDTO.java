package com.sophossolutions.bandcamp.dto;

import com.sophossolutions.bandcamp.model.AlbumType;
import com.sophossolutions.bandcamp.model.Band;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
public class AlbumDTO {
    private Integer id;
    private String name;
    private AlbumType type;
    private LocalDate releaseDate;
    private Set<Band> authors;
}

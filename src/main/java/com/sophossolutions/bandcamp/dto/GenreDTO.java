package com.sophossolutions.bandcamp.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
public class GenreDTO {
    private Integer id;
    private String name;
}

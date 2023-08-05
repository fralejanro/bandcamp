package com.sophossolutions.bandcamp.dto;

import com.sophossolutions.bandcamp.model.Country;
import com.sophossolutions.bandcamp.model.Genre;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
public class BandDTO {
    private Integer id;
    private String name;
    private Genre genre;
    private Country countryOfOrigin;
    private Integer yearOfCreation;
    private Boolean status;

}

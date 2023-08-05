package com.sophossolutions.bandcamp.model;

import com.sophossolutions.bandcamp.dto.BandDTO;
import com.sophossolutions.bandcamp.dto.GenreDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class Band {
    @Id
    private Integer id;
    private String name;
    private Integer genre;
    private Integer countryOfOrigin;
    private Integer yearOfCreation;
    private Boolean status;

    public BandDTO toDTO(Band band) {
        return BandDTO.builder()
                .id(band.getId())
                .name(band.getName())
                .yearOfCreation(band.yearOfCreation)
                .status(band.getStatus())
                .build();
    }
}

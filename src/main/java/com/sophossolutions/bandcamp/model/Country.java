package com.sophossolutions.bandcamp.model;

import com.sophossolutions.bandcamp.dto.CountryDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class Country {
    @Id
    private Integer id;
    private String name;

    public CountryDTO toDTO(Country country) {
        return CountryDTO.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }
}

package com.sophossolutions.bandcamp.model;

import com.sophossolutions.bandcamp.dto.AlbumTypeDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class AlbumType {
    @Id
    private Integer id;
    private String name;

    public AlbumTypeDTO toDTO(AlbumType albumType) {
        return AlbumTypeDTO.builder()
                .id(albumType.getId())
                .name(albumType.getName())
                .build();
    }
}

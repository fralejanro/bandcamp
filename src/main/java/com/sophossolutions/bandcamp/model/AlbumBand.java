package com.sophossolutions.bandcamp.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder(toBuilder = true)
public class AlbumBand {
    @Id
    private Integer id;
    private Integer albumId;
    private Integer bandId;
}

package com.sophossolutions.bandcamp.model;

import com.sophossolutions.bandcamp.dto.AlbumDTO;
import reactor.core.publisher.Mono;

public interface BandActions {
     Mono<AlbumDTO> addAlbumBand(Band band, Album album);
}

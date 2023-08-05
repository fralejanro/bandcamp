package com.sophossolutions.bandcamp.service;


import com.sophossolutions.bandcamp.dto.AlbumDTO;
import com.sophossolutions.bandcamp.model.Album;
import com.sophossolutions.bandcamp.model.AlbumBand;
import com.sophossolutions.bandcamp.model.Band;
import com.sophossolutions.bandcamp.model.BandActions;
import com.sophossolutions.bandcamp.repository.AlbumBandRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


@Service
public class AlbumBandService implements BandActions {

    private final AlbumBandRepository albumBandRepository;

    public AlbumBandService(AlbumBandRepository albumBandRepository) {
        this.albumBandRepository = albumBandRepository;
    }

    @Override
    public Mono<AlbumDTO> addAlbumBand(Band band, Album album) {
        return Mono.just(album)
                .map(alb -> {
                    albumBandRepository.save(AlbumBand.builder()
                            .bandId(band.getId())
                            .albumId(alb.getId())
                            .build());
                    return alb.toDTO(alb);
                });
    }
}

package com.sophossolutions.bandcamp.service;

import com.sophossolutions.bandcamp.dto.AlbumTypeDTO;
import com.sophossolutions.bandcamp.model.AlbumType;
import com.sophossolutions.bandcamp.repository.AlbumTypeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AlbumTypeService {
    private final AlbumTypeRepository albumTypeRepository;

    public AlbumTypeService(AlbumTypeRepository albumTypeRepository) {
        this.albumTypeRepository = albumTypeRepository;
    }

    public Mono<AlbumTypeDTO> findById(Integer id) {
        return albumTypeRepository.findById(id).map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo album con id= " + id
                        + " no encontrado").getMostSpecificCause()));
    }

    public Flux<AlbumTypeDTO> findAll() {
        return albumTypeRepository.findAll().map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No se encontraron tipos de album")
                        .getMostSpecificCause()));
    }

    public Mono<AlbumTypeDTO> createAlbumType(AlbumType genre) {
        return saveAlbumType(genre);
    }

    public Mono<AlbumTypeDTO> updateAlbumType(AlbumType genre) {
        return albumTypeRepository.findById(genre.getId())
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Tipo album no actualizado")
                        .getMostSpecificCause()))
                .flatMap(g -> saveAlbumType(genre));
    }

    private Mono<AlbumTypeDTO> saveAlbumType(AlbumType genre) {
        return albumTypeRepository.save(genre).map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo album no persistido")
                        .getMostSpecificCause()));
    }

    public Mono<AlbumTypeDTO> deleteAlbumTypeById(Integer id) {
        return albumTypeRepository.findById(id).flatMap(g -> albumTypeRepository.delete(g).thenReturn(g.toDTO(g)))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Tipo album con id= " + id
                        + " no borrado").getMostSpecificCause()));
    }
}

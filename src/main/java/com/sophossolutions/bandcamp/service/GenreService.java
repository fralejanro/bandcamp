package com.sophossolutions.bandcamp.service;

import com.sophossolutions.bandcamp.dto.GenreDTO;
import com.sophossolutions.bandcamp.model.Genre;
import com.sophossolutions.bandcamp.repository.GenreRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public Mono<GenreDTO> findById(Integer id) {
        return genreRepository.findById(id).map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero con id= " + id
                        + " no encontrado").getMostSpecificCause()));
    }

    public Flux<GenreDTO> findAll() {
        return genreRepository.findAll().map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron generos")
                        .getMostSpecificCause()));
    }

    public Mono<GenreDTO> createGenre(Genre genre) {
        return saveGenre(genre);
    }

    public Mono<GenreDTO> updateGenre(Genre genre) {
        return genreRepository.findById(genre.getId())
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Genero no actualizado")
                        .getMostSpecificCause()))
                .flatMap(g -> saveGenre(genre));
    }

    private Mono<GenreDTO> saveGenre(Genre genre) {
        return genreRepository.save(genre).map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero no persistido")
                        .getMostSpecificCause()));
    }

    public Mono<GenreDTO> deleteGenreById(Integer id) {
        return genreRepository.findById(id).flatMap(g -> genreRepository.delete(g).thenReturn(g.toDTO(g)))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero con id= " + id
                        + " no borrado").getMostSpecificCause()));
    }

}

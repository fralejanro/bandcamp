package com.sophossolutions.bandcamp.controller;

import com.sophossolutions.bandcamp.dto.GenreDTO;
import com.sophossolutions.bandcamp.model.Genre;
import com.sophossolutions.bandcamp.service.GenreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GenreControllerTest {

    @Mock
    private GenreService genreService;

    @InjectMocks
    private GenreController genreController;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getGenreById() {
        Integer id =1;
        when(genreController.getGenreById(id)).thenReturn(Mono.just(GenreDTO.builder().id(id).build()));
        Mono<GenreDTO> genre = genreController.getGenreById(id);
        assertEquals(Objects.requireNonNull(genre.block()).getId(),1);
    }

    @Test
    void getAllGenres() {
        GenreDTO genre1 = GenreDTO.builder().id(1).name("Hardcore").build();
        GenreDTO genre2 = GenreDTO.builder().id(2).name("Punk").build();
        Flux<GenreDTO> genres = Flux.just(genre1, genre2);
        when(genreController.getAllGenres()).thenReturn(genres);
        StepVerifier.create(genreController.getAllGenres())
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void createGenre() {
        GenreDTO genre = GenreDTO.builder().id(1).name("Hardcore").build();
        when(genreController.createGenre(genre)).thenReturn(Mono.just(genre));
        StepVerifier.create(genreController.createGenre(genre))
                .expectNext(genre)
                .verifyComplete();
    }

    @Test
    void updateGenre() {
        Genre genre = Genre.builder().id(1).name("Punk").build();
        GenreDTO genreDTO = genre.toDTO(genre);
        when(genreService.updateGenre(genre)).thenReturn(Mono.just(genreDTO));
        StepVerifier.create(genreController.updateGenre(genreDTO))
                .expectNext(genreDTO)
                .verifyComplete();
    }

    @Test
    void deleteGenreById() {
        Integer id =1;
        when(genreController.deleteGenreById(id)).thenReturn(Mono.just(GenreDTO.builder().id(id).build()));
        Mono<GenreDTO> genre = genreController.deleteGenreById(id);
        assertEquals(Objects.requireNonNull(genre.block()).getId(),1);
    }
}
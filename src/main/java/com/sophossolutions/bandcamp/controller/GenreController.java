package com.sophossolutions.bandcamp.controller;

import com.sophossolutions.bandcamp.dto.GenreDTO;
import com.sophossolutions.bandcamp.model.Genre;
import com.sophossolutions.bandcamp.service.GenreService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/genre")
public class GenreController {

    private final GenreService genreService;

    public GenreController(GenreService genreService){
        this.genreService = genreService;
    }

    @GetMapping("/{id}")
    public Mono<GenreDTO> getGenreById(@PathVariable Integer id){
        return genreService.findById(id);
    }

    @GetMapping()
    public Flux<GenreDTO> getAllGenres(){
        return genreService.findAll();
    }

    @PostMapping
    public Mono<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO){
        return genreService.createGenre(Genre.builder().name(genreDTO.getName()).build());
    }

    @PutMapping
    public Mono<GenreDTO> updateGenre(@RequestBody GenreDTO genreDTO){
        return genreService.updateGenre(Genre.builder().id(genreDTO.getId()).name(genreDTO.getName()).build());
    }

    @DeleteMapping("/{id}")
    public Mono<GenreDTO> deleteGenreById(@PathVariable Integer id){
        return genreService.deleteGenreById(id);
    }
}

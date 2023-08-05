package com.sophossolutions.bandcamp.controller;


import com.sophossolutions.bandcamp.dto.AlbumTypeDTO;
import com.sophossolutions.bandcamp.model.AlbumType;
import com.sophossolutions.bandcamp.service.AlbumTypeService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/albumType")
public class AlbumTypeController {

    private final AlbumTypeService albumTypeService;

    public AlbumTypeController(AlbumTypeService albumTypeService){
        this.albumTypeService = albumTypeService;
    }

    @GetMapping("/{id}")
    public Mono<AlbumTypeDTO> getAlbumTypeById(@PathVariable Integer id){
        return albumTypeService.findById(id);
    }

    @GetMapping()
    public Flux<AlbumTypeDTO> getAllAlbumTypes(){
        return albumTypeService.findAll();
    }

    @PostMapping
    public Mono<AlbumTypeDTO> createAlbumType(@RequestBody AlbumTypeDTO albumTypeDTO){
        return albumTypeService.createAlbumType(AlbumType.builder().name(albumTypeDTO.getName()).build());
    }

    @PutMapping
    public Mono<AlbumTypeDTO> updateAlbumType(@RequestBody AlbumTypeDTO albumTypeDTO){
        return albumTypeService.updateAlbumType(AlbumType.builder().id(albumTypeDTO.getId()).name(albumTypeDTO.getName()).build());
    }

    @DeleteMapping("/{id}")
    public Mono<AlbumTypeDTO> deleteAlbumTypeById(@PathVariable Integer id){
        return albumTypeService.deleteAlbumTypeById(id);
    }
}

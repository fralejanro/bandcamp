package com.sophossolutions.bandcamp.controller;

import com.sophossolutions.bandcamp.dto.BandDTO;
import com.sophossolutions.bandcamp.model.Band;
import com.sophossolutions.bandcamp.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/band")
public class BandController {

    @Autowired
    private BandService bandService;

    @GetMapping("/{id}")
    public Mono<Band> getBandById(@PathVariable Integer id) {
        return bandService.findById(id);
    }

    @GetMapping()
    public Flux<Band> getAllBands() {
        return bandService.findAll();
    }

    @PostMapping()
    public Mono<BandDTO> createBand(@RequestBody BandDTO bandDTO) {
        return bandService.createBand(
                Band.builder()
                        .name(bandDTO.getName())
                        .yearOfCreation(bandDTO.getYearOfCreation())
                        .status(bandDTO.getStatus())
                        .build()
        );
    }
}

package com.sophossolutions.bandcamp.controller;

import com.sophossolutions.bandcamp.dto.CountryDTO;
import com.sophossolutions.bandcamp.model.Country;
import com.sophossolutions.bandcamp.service.CountryService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/country")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/{id}")
    public Mono<CountryDTO> getCountryById(@PathVariable Integer id) {
        return countryService.findById(id);
    }

    @GetMapping()
    public Flux<CountryDTO> getAllCountries() {
        return countryService.findAll();
    }

    @PostMapping
    public Mono<CountryDTO> createCountry(@RequestBody CountryDTO countryDTO) {
        return countryService.createCountry(Country.builder().name(countryDTO.getName()).build());
    }

    @PutMapping
    public Mono<CountryDTO> updateCountry(@RequestBody CountryDTO countryDTO) {
        return countryService.updateCountry(Country.builder()
                .id(countryDTO.getId())
                .name(countryDTO.getName())
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public Mono<CountryDTO> deleteCountryById(@PathVariable Integer id) {
        return countryService.deleteCountryById(id);
    }
}

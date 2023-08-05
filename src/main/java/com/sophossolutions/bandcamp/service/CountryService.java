package com.sophossolutions.bandcamp.service;

import com.sophossolutions.bandcamp.dto.CountryDTO;
import com.sophossolutions.bandcamp.model.Country;
import com.sophossolutions.bandcamp.repository.CountryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public Mono<CountryDTO> findById(Integer id) {
        return countryRepository.findById(id).map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Genero con id= " + id
                        + " no encontrado").getMostSpecificCause()));
    }

    public Flux<CountryDTO> findAll() {
        return countryRepository.findAll().map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron generos")
                        .getMostSpecificCause()));
    }

    public Mono<CountryDTO> createCountry(Country country) {
        return saveCountry(country);
    }

    public Mono<CountryDTO> updateCountry(Country country) {
        return countryRepository.findById(country.getId())
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "País no actualizado")
                        .getMostSpecificCause()))
                .flatMap(g -> saveCountry(country));
    }

    private Mono<CountryDTO> saveCountry(Country country) {
        return countryRepository.save(country).map(g -> g.toDTO(g))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "País no persistido")
                        .getMostSpecificCause()));
    }

    public Mono<CountryDTO> deleteCountryById(Integer id) {
        return countryRepository.findById(id).flatMap(g -> countryRepository.delete(g).thenReturn(g.toDTO(g)))
                .onErrorResume(throwable -> Mono.empty())
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "País con id= " + id
                        + " no borrado").getMostSpecificCause()));
    }
}

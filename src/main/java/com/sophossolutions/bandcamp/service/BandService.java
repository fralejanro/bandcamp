package com.sophossolutions.bandcamp.service;

import com.sophossolutions.bandcamp.dto.BandDTO;
import com.sophossolutions.bandcamp.model.Band;
import com.sophossolutions.bandcamp.repository.BandRepository;
import com.sophossolutions.bandcamp.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BandService {

    @Autowired
    private BandRepository bandRepository;

    @Autowired
    private CountryRepository countryRepository;

    public Mono<Band> findById(Integer id){
        return bandRepository.findById(id).map(m->{
            System.out.println(m);
            return m;
        });
    }

    public Flux<Band> findAll(){ return bandRepository.findAll();}

    public Mono<BandDTO> createBand(Band band){
//        Band band = new Band();
////        band.setName(bandDTO.getName());
////        band.setStatus(bandDTO.getStatus());
////        band.setYearOfCreation(bandDTO.getYearOfCreation());
////     //   band.setGenres(new HashSet<>());
////        band.setCountryOfOrigin(bandDTO.getCountryOfOrigin().getId());
////        return  bandRepository.save(band);
        return null;
    }

}

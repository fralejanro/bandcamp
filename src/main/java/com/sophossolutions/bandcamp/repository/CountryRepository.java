package com.sophossolutions.bandcamp.repository;
import com.sophossolutions.bandcamp.model.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends R2dbcRepository<Country,Integer> {

}

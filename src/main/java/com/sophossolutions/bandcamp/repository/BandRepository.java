package com.sophossolutions.bandcamp.repository;
import com.sophossolutions.bandcamp.model.Band;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepository extends R2dbcRepository<Band,Integer> {

}

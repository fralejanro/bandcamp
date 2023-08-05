package com.sophossolutions.bandcamp.repository;

import com.sophossolutions.bandcamp.model.AlbumBand;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepository extends R2dbcRepository<AlbumBand,Integer> {

}

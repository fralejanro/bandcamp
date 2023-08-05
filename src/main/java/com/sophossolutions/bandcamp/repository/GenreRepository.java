package com.sophossolutions.bandcamp.repository;

import com.sophossolutions.bandcamp.model.Genre;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends R2dbcRepository<Genre,Integer> {
}

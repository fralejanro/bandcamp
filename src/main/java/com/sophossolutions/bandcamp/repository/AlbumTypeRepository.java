package com.sophossolutions.bandcamp.repository;
import com.sophossolutions.bandcamp.model.AlbumType;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumTypeRepository extends R2dbcRepository<AlbumType,Integer> {
}

package com.example.heartshop.service;

import com.example.heartshop.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    Optional<Artist> findById(Long id);
    List<Artist> findAll();
    Optional<Artist> save(String name, String address);
    void deleteById(Long id);

}

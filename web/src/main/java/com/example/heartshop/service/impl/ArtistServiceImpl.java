package com.example.heartshop.service.impl;

import com.example.heartshop.model.Artist;
import com.example.heartshop.repository.ArtistRepositoryJPA;
import com.example.heartshop.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    private final ArtistRepositoryJPA artistRepositoryJPA;


    public ArtistServiceImpl(ArtistRepositoryJPA artistRepositoryJPA) {
        this.artistRepositoryJPA = artistRepositoryJPA;
    }

    public Optional<Artist> findById(Long id) {
        return this.artistRepositoryJPA.findById(id);
    }

    public List<Artist> findAll() {
        return this.artistRepositoryJPA.findAll();
    }

    public Optional<Artist> save(String name, String address) {
        return Optional.of(this.artistRepositoryJPA.save(new Artist(name, address)));
    }

    public void deleteById(Long id) {
        this.artistRepositoryJPA.deleteById(id);
    }

}

package com.example.heartshop.repository;

import com.example.heartshop.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepositoryJPA extends JpaRepository<Artist, Long> {
}

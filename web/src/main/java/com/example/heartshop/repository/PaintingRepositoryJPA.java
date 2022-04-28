package com.example.heartshop.repository;

import com.example.heartshop.model.Painting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaintingRepositoryJPA extends JpaRepository<Painting, Long> {
    Optional<Painting> findByName(String name);
    void deleteByName(String name);
}


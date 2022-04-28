package com.example.heartshop.service;

import com.example.heartshop.model.Painting;
import com.example.heartshop.model.dto.PaintingDTO;

import java.util.List;
import java.util.Optional;

public interface PaintingService {

    List<Painting> findAll();

    Optional<Painting> findById(Long id);

    Optional<Painting> findByName(String name);

    Optional<Painting> save(String name, Double price, Integer quantity, Long category, Long artist);

    Optional<Painting> save(PaintingDTO productDto);

    Optional<Painting> edit(Long id, String name, Double price, Integer quantity, Long category, Long artist);

    Optional<Painting> edit(Long id, PaintingDTO productDto);

    void deleteById(Long id);

    void refreshMaterializedView();
}

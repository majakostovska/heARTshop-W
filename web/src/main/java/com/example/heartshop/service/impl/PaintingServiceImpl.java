package com.example.heartshop.service.impl;

import com.example.heartshop.model.Artist;
import com.example.heartshop.model.Category;
import com.example.heartshop.model.Painting;
import com.example.heartshop.model.dto.PaintingDTO;
import com.example.heartshop.model.events.PaintingCreatedEvent;
import com.example.heartshop.model.exceptions.ArtistNotFoundException;
import com.example.heartshop.model.exceptions.CategoryNotFoundException;
import com.example.heartshop.model.exceptions.PaintingNotFoundException;
import com.example.heartshop.repository.ArtistRepositoryJPA;
import com.example.heartshop.repository.CategoryRepositoryJPA;
import com.example.heartshop.repository.PaintingRepositoryJPA;
import com.example.heartshop.repository.views.PaintingsPerArtistViewRepository;
import com.example.heartshop.service.PaintingService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PaintingServiceImpl implements PaintingService {

    private final PaintingRepositoryJPA paintingRepositoryJPA;
    private final ArtistRepositoryJPA artistRepositoryJPA;
    private final CategoryRepositoryJPA categoryRepositoryJPA;
    private final PaintingsPerArtistViewRepository paintingsPerArtistViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public PaintingServiceImpl(PaintingRepositoryJPA paintingRepositoryJPA,
                               ArtistRepositoryJPA artistRepositoryJPA,
                               CategoryRepositoryJPA categoryRepositoryJPA,
                               PaintingsPerArtistViewRepository paintingsPerArtistViewRepository,
                              ApplicationEventPublisher applicationEventPublisher) {
        this.paintingRepositoryJPA = paintingRepositoryJPA;
        this.artistRepositoryJPA = artistRepositoryJPA;
        this.categoryRepositoryJPA = categoryRepositoryJPA;
        this.paintingsPerArtistViewRepository = paintingsPerArtistViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Painting> findAll() {
        return this.paintingRepositoryJPA.findAll();
    }

    @Override
    public Optional<Painting> findById(Long id) {
        return this.paintingRepositoryJPA.findById(id);
    }

    @Override
    public Optional<Painting> findByName(String name) {
        return this.paintingRepositoryJPA.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Painting> save(String name, Double price, Integer quantity, Long categoryId, Long artistId) {
        Category category = this.categoryRepositoryJPA.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        Artist artist = this.artistRepositoryJPA.findById(artistId)
                .orElseThrow(() -> new ArtistNotFoundException(artistId));

        this.paintingRepositoryJPA.deleteByName(name);
        Painting painting = new Painting(name, price, quantity, category, artist);
        this.paintingRepositoryJPA.save(painting);
        //this.refreshMaterializedView();

        this.applicationEventPublisher.publishEvent(new PaintingCreatedEvent(painting));
        return Optional.of(painting);
    }

    @Override
    public Optional<Painting> save(PaintingDTO productDto) {
        Category category = this.categoryRepositoryJPA.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        Artist artist = this.artistRepositoryJPA.findById(productDto.getArtist())
                .orElseThrow(() -> new ArtistNotFoundException(productDto.getArtist()));

        this.paintingRepositoryJPA.deleteByName(productDto.getName());
        Painting painting = new Painting(productDto.getName(), productDto.getPrice(), productDto.getQuantity(), category, artist);
        this.paintingRepositoryJPA.save(painting);
        //this.refreshMaterializedView();

        this.applicationEventPublisher.publishEvent(new PaintingCreatedEvent(painting));
        return Optional.of(painting);
    }

    @Override
    @Transactional
    public Optional<Painting> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long manufacturerId) {

        Painting product = this.paintingRepositoryJPA.findById(id).orElseThrow(() -> new PaintingNotFoundException(id));

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);

        Category category = this.categoryRepositoryJPA.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        product.setCategory(category);

        Artist artist = this.artistRepositoryJPA.findById(manufacturerId)
                .orElseThrow(() -> new ArtistNotFoundException(manufacturerId));
        product.setArtist(artist);

        this.paintingRepositoryJPA.save(product);
        return Optional.of(product);
    }

    @Override
    public Optional<Painting> edit(Long id, PaintingDTO productDto) {
        Painting painting = this.paintingRepositoryJPA.findById(id).orElseThrow(() -> new PaintingNotFoundException(id));

        painting.setName(productDto.getName());
        painting.setPrice(productDto.getPrice());
        painting.setQuantity(productDto.getQuantity());

        Category category = this.categoryRepositoryJPA.findById(productDto.getCategory())
                .orElseThrow(() -> new CategoryNotFoundException(productDto.getCategory()));
        painting.setCategory(category);

        Artist artist = this.artistRepositoryJPA.findById(productDto.getArtist())
                .orElseThrow(() -> new ArtistNotFoundException(productDto.getArtist()));
        painting.setArtist(artist);

        this.paintingRepositoryJPA.save(painting);
        return Optional.of(painting);
    }

    @Override
    public void deleteById(Long id) {
        this.paintingRepositoryJPA.deleteById(id);
    }

    @Override
    public void refreshMaterializedView() {
//        this.productsPerManufacturerViewRepository.refreshMaterializedView();
    }
}

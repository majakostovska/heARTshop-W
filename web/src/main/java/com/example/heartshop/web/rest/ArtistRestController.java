package com.example.heartshop.web.rest;


import com.example.heartshop.model.Artist;
import com.example.heartshop.service.ArtistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/artists")
public class ArtistRestController {

    private final ArtistService artistService;

    public ArtistRestController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> findAll() {
        return this.artistService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findById(@PathVariable Long id) {
        return this.artistService.findById(id)
                .map(artist -> ResponseEntity.ok().body(artist))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Artist> save(@RequestParam String name, @RequestParam String address) {
        return this.artistService.save(name, address)
                .map(artist -> ResponseEntity.ok().body(artist))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.artistService.deleteById(id);
        if(this.artistService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}

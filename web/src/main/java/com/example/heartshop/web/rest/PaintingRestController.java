package com.example.heartshop.web.rest;

import com.example.heartshop.model.Painting;
import com.example.heartshop.model.dto.PaintingDTO;
import com.example.heartshop.service.PaintingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/paintings")
public class PaintingRestController {

    private final PaintingService paintingService;

    public PaintingRestController(PaintingService paintingService) {
        this.paintingService = paintingService;
    }

    @GetMapping
    private List<Painting> findAll() {
        return this.paintingService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Painting> findById(@PathVariable Long id) {
        return this.paintingService.findById(id)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Painting> save(@RequestBody PaintingDTO productDto) {
        return this.paintingService.save(productDto)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Painting> save(@PathVariable Long id, @RequestBody PaintingDTO paintingDTO) {
        return this.paintingService.edit(id, paintingDTO)
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.paintingService.deleteById(id);
        if(this.paintingService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}

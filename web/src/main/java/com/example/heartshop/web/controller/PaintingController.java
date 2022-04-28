package com.example.heartshop.web.controller;

import com.example.heartshop.model.Artist;
import com.example.heartshop.model.Category;
import com.example.heartshop.model.Painting;
import com.example.heartshop.service.ArtistService;
import com.example.heartshop.service.CategoryService;
import com.example.heartshop.service.PaintingService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/paintings")
public class PaintingController {

    private final PaintingService paintingService;
    private final CategoryService categoryService;
    private final ArtistService artistService;

    public PaintingController(PaintingService paintingService,
                             CategoryService categoryService,
                             ArtistService artistService) {
        this.paintingService = paintingService;
        this.categoryService = categoryService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Painting> paintings = this.paintingService.findAll();
        model.addAttribute("paintings", paintings);
        model.addAttribute("bodyContent", "paintings");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.paintingService.deleteById(id);
        return "redirect:/paintings";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.paintingService.findById(id).isPresent()) {
            Painting paintings = this.paintingService.findById(id).get();
            List<Artist> artists = this.artistService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("artists", artists);
            model.addAttribute("categories", categories);
            model.addAttribute("paintings", paintings);
            model.addAttribute("bodyContent", "add-painting");
            return "master-template";
        }
        return "redirect:/paintings?error=PaintingNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<Artist> artists = this.artistService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("artists", artists);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-painting");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam Long category,
            @RequestParam Long artist) {
        if (id != null) {
            this.paintingService.edit(id, name, price, quantity, category, artist);
        } else {
            this.paintingService.save(name, price, quantity, category, artist);
        }
        return "redirect:/paintings";
    }
}


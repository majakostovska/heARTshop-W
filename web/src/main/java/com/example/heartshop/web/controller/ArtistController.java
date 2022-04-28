package com.example.heartshop.web.controller;

import com.example.heartshop.service.ArtistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public String getCategoryPage(Model model){
        model.addAttribute("artists", this.artistService.findAll());
        model.addAttribute("bodyContent", "artists");
        return "master-template";
    }
}


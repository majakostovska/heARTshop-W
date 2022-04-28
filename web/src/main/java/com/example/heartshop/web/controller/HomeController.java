package com.example.heartshop.web.controller;

import com.example.heartshop.model.Painting;
import com.example.heartshop.service.ArtistService;
import com.example.heartshop.service.PaintingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping({"/home","/"})
public class HomeController {
    private final PaintingService paintingService;
    private final ArtistService artistService;


    public HomeController(PaintingService paintingService, ArtistService artistService) {
        this.paintingService = paintingService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getPaintingPage(@RequestParam(required = false) String error, Model model)
    {
        List<Painting> paintings = this.paintingService.findAll();
        model.addAttribute("paintings", paintings);
        model.addAttribute("bodyContent", "paintings");
        return "master-template";
    }


}

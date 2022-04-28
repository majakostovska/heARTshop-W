package com.example.heartshop.model.dto;

import lombok.Data;

@Data
public class PaintingDTO {
    private String name;

    private Double price;

    private Integer quantity;

    private Long category;

    private Long artist;

    public PaintingDTO() {
    }

    public PaintingDTO(String name, Double price, Integer quantity, Long category, Long artist) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.artist = artist;
    }

}

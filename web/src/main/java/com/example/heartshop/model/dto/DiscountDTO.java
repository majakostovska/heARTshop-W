package com.example.heartshop.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiscountDTO {

    private LocalDateTime validUntil;

    public DiscountDTO() {
    }

    public DiscountDTO(LocalDateTime validUntil) {
        this.validUntil = validUntil;
    }
}


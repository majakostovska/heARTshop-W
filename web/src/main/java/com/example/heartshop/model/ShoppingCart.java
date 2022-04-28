package com.example.heartshop.model;

import com.example.heartshop.model.enums.ShoppingCartStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDateTime dateCreated;

    @Enumerated(EnumType.STRING)
    private ShoppingCartStatus status;


    @ManyToMany(fetch = FetchType.EAGER)
    private List<Painting> paintings;

    public ShoppingCart(Long id, User user, LocalDateTime dateCreated, ShoppingCartStatus status, List<Painting> paintings) {
        this.id = id;
        this.user = user;
        this.dateCreated = dateCreated;
        this.status = status;
        this.paintings = paintings;
    }

    public ShoppingCart(User user, LocalDateTime dateCreated, ShoppingCartStatus status, List<Painting> paintings) {
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.status = ShoppingCartStatus.CREATED;
        this.paintings = new ArrayList<>();
    }

    public ShoppingCart()
    {

    }

    public ShoppingCart(User user) {
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.paintings = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;

    }
}

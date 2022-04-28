package com.example.heartshop.service;

import com.example.heartshop.model.Painting;
import com.example.heartshop.model.ShoppingCart;

import javax.persistence.criteria.Order;
import java.util.List;

public interface ShoppingCartService {
    List<Painting> listAllPaintingsInShoppingCart(Long cartId);
    ShoppingCart getActiveShoppingCart(String username);
    ShoppingCart addPaintingToShoppingCart(String username, Long productId);

}

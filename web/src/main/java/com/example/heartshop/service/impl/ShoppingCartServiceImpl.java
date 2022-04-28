package com.example.heartshop.service.impl;

import com.example.heartshop.model.Painting;
import com.example.heartshop.model.ShoppingCart;
import com.example.heartshop.model.User;
import com.example.heartshop.model.enums.ShoppingCartStatus;
import com.example.heartshop.model.exceptions.*;
import com.example.heartshop.repository.ShoppingCartRepositoryJPA;
import com.example.heartshop.repository.UserRepositoryJPA;
import com.example.heartshop.service.PaintingService;
import com.example.heartshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepositoryJPA shoppingCartRepositoryJPA;
    private final UserRepositoryJPA userRepositoryJPA;
    private final PaintingService paintingService;

    public ShoppingCartServiceImpl(ShoppingCartRepositoryJPA shoppingCartRepositoryJPA, UserRepositoryJPA userRepositoryJPA, PaintingService paintingService) {
        this.shoppingCartRepositoryJPA = shoppingCartRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
        this.paintingService = paintingService;
    }

    public List<Painting> listAllPaintingsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepositoryJPA.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepositoryJPA.findById(cartId).get().getPaintings();
    }

    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepositoryJPA.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return this.shoppingCartRepositoryJPA
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.shoppingCartRepositoryJPA.save(cart);
                });
    }

    public ShoppingCart addPaintingToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Painting painting = this.paintingService.findById(productId)
                .orElseThrow(() -> new PaintingNotFoundException(productId));
        if(shoppingCart.getPaintings()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        shoppingCart.getPaintings().add(painting);
        return this.shoppingCartRepositoryJPA.save(shoppingCart);
    }

}

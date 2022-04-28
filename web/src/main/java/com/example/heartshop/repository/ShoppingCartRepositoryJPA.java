package com.example.heartshop.repository;

import com.example.heartshop.model.ShoppingCart;
import com.example.heartshop.model.User;
import com.example.heartshop.model.enums.ShoppingCartStatus;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepositoryJPA extends JpaRepository<ShoppingCart,Long> {
    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}

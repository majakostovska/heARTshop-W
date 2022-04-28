package com.example.heartshop.repository;


import com.example.heartshop.model.Discount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DiscountRepositoryJPA extends JpaRepository<Discount, Long> {

    Page<Discount> findAll(Pageable pageable);
}


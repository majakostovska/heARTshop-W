package com.example.heartshop.service;

import com.example.heartshop.model.Discount;
import com.example.heartshop.model.dto.DiscountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface DiscountService {

    List<Discount> findAll();

    Page<Discount> findAllWithPagination(Pageable pageable);

    Optional<Discount> findById(Long id);

    Optional<Discount> save(DiscountDTO discountDto);

    Optional<Discount> edit(Long id, DiscountDTO discountDto);

    void deleteById(Long id);

    Optional<Discount> assignDiscount(String username, Long discountId);
}

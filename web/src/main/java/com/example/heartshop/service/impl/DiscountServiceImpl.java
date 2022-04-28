package com.example.heartshop.service.impl;

import com.example.heartshop.model.Discount;
import com.example.heartshop.model.User;
import com.example.heartshop.model.dto.DiscountDTO;
import com.example.heartshop.model.exceptions.DiscountNotFoundException;
import com.example.heartshop.model.exceptions.UserNotFoundException;
import com.example.heartshop.repository.DiscountRepositoryJPA;
import com.example.heartshop.repository.UserRepositoryJPA;
import com.example.heartshop.service.DiscountService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountServiceImpl implements DiscountService {

    private final DiscountRepositoryJPA discountRepository;
    private final UserRepositoryJPA userRepository;

    public DiscountServiceImpl(DiscountRepositoryJPA discountRepository, UserRepositoryJPA userRepository) {
        this.discountRepository = discountRepository;
        this.userRepository=userRepository;
    }

    @Override
    public List<Discount> findAll() {
        return this.discountRepository.findAll();
    }

    @Override
    public Page<Discount> findAllWithPagination(Pageable pageable) {
        return this.discountRepository.findAll(pageable);
    }


    @Override
    public Optional<Discount> findById(Long id) {
        return this.discountRepository.findById(id);
    }

    @Override
    public Optional<Discount> save(DiscountDTO discountDto) {
        return Optional.of(this.discountRepository.save(new Discount(discountDto.getValidUntil())));
    }

    @Override
    public Optional<Discount> edit(Long id, DiscountDTO discountDto) {
        Discount discount = this.discountRepository.findById(id)
                .orElseThrow(() -> new DiscountNotFoundException(id));
        discount.setValidUntil(discountDto.getValidUntil());
        return Optional.of(this.discountRepository.save(discount));
    }

    @Override
    public void deleteById(Long id) {
        this.discountRepository.deleteById(id);
    }

    @Override
    public Optional<Discount> assignDiscount(String username, Long discountId) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        Discount discount = this.discountRepository.findById(discountId)
                .orElseThrow(() -> new DiscountNotFoundException(discountId));

        discount.getUsers().add(user);
        return Optional.of(this.discountRepository.save(discount));
    }
}

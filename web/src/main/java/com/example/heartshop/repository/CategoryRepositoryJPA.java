package com.example.heartshop.repository;

import com.example.heartshop.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepositoryJPA extends JpaRepository<Category,Long> {
    List<Category> findAllByNameLike(String text);
    void deleteByName(String name);

}

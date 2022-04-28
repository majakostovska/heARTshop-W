package com.example.heartshop.repository.views;

import com.example.heartshop.model.views.PaintingsPerCategoryView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaintingsPerCategoryViewRepository
extends JpaRepository<PaintingsPerCategoryView, Long>
{

}

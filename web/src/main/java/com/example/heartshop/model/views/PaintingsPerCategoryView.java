package com.example.heartshop.model.views;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Subselect("SELECT * FROM public.paintings_per_category")
@Immutable
public class PaintingsPerCategoryView {

    @Id
    @Column(name = "category_id")
    private Long categoryId;

    @Column(name="num_paintings")
    private Integer numPaintings;
}

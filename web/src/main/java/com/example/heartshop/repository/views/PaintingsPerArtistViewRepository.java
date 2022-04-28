package com.example.heartshop.repository.views;

import com.example.heartshop.model.views.PaintingsPerArtistView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PaintingsPerArtistViewRepository extends JpaRepository<PaintingsPerArtistView,Long> {

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "REFRESH MATERIALIZED VIEW public.paintings_per_artist", nativeQuery = true)
    void refreshMaterializedView();
}

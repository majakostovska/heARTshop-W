package com.example.heartshop.repository;

import com.example.heartshop.model.User;
import com.example.heartshop.model.projections.UserProjection;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepositoryJPA extends JpaRepository<User,Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"carts"})
    @Query("select u from User u")
    List<User> fetchAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"carts", "discount"})
    @Query("select u from User u")
    List<User> loadAll();

    Optional<User> findByUsername(String text);
    Optional<User> findByUsernameAndPassword(String name, String password);

    @Query("select u.username, u.name, u.surname from User u")
    List<UserProjection> takeUsernameAndNameAndSurnameByProjection();

    //User save(User user);
}

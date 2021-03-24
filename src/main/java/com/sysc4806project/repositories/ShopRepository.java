package com.sysc4806project.repositories;

import com.sysc4806project.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    @Query("FROM Shop s WHERE s.owner.id = :userId")
    public List<Shop> getAllShopsCurrentUser(@Param("userId") Long userId);
}

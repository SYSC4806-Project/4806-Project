package com.sysc4806project.repositories;

import com.sysc4806project.models.Shop;
import com.sysc4806project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Shop findByName(String shopName);
    List<Shop> findByCategoryList(String cat);
    List<Shop> findByOwnerId(Long user_id);
}

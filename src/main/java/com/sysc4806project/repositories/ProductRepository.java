package com.sysc4806project.repositories;

import com.sysc4806project.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getById(Long id);
    List<Product> findByParentShopId(Long shop_id);
}

package com.sysc4806project.repositories;

import com.sysc4806project.models.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> findByParentShopId(Long shop_id);
}

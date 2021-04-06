package com.sysc4806project.services;

import com.sysc4806project.models.Product;
import com.sysc4806project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProductsWithinShop(Long shopId) {
        return productRepository.findByParentShopId(shopId);
    }
}

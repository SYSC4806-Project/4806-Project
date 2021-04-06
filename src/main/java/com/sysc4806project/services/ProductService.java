package com.sysc4806project.services;

import com.sysc4806project.dto.ProductDTO;
import com.sysc4806project.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProductsWithinShop(Long shopId);
    public void addProduct(Product product);
    public Product dtoConvertToProductObject(ProductDTO productDTO);
}

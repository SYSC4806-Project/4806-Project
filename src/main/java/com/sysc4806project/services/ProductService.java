package com.sysc4806project.services;

import com.sysc4806project.dto.ProductDTO;
import com.sysc4806project.models.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProductsWithinShop(Long shopId);
    public Product getProductById(Long id);

    public void addProduct(Product product);
    public Product dtoConvertToProductObject(ProductDTO productDTO);
    public void removeProductById(Long productId);
}

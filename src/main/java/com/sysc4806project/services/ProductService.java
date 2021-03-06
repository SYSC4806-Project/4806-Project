package com.sysc4806project.services;

import com.sysc4806project.dto.ProductDTO;
import com.sysc4806project.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProductsWithinShop(Long shopId);
    public Product getProductById(Long id);

    public void addProduct(Product product, MultipartFile file);
    public Product dtoConvertToProductObject(ProductDTO productDTO);
    public ProductDTO dtoConvertFromProductObject(Product product);

    public void removeProductById(Long productId);
}

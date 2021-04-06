package com.sysc4806project.services;

import com.sysc4806project.dto.ProductDTO;
import com.sysc4806project.models.Product;
import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopService shopService;

    @Override
    public List<Product> getAllProductsWithinShop(Long shopId) {
        return productRepository.findByParentShopId(shopId);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product dtoConvertToProductObject(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        Optional<Shop> shopOptional = shopService.getShopById(productDTO.getParentShopId());
        if (shopOptional.isPresent()) {
            Shop shop = shopOptional.get();
            product.setParentShop(shop);
        }
        product.setDescription(productDTO.getDescription());
        product.setInventoryNum(productDTO.getInventoryNum());
        product.setPrice(productDTO.getPrice());
        return product;
    }
}

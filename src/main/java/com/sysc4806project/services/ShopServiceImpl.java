package com.sysc4806project.services;

import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopRepository shopRepository;

    @Override
    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }
}

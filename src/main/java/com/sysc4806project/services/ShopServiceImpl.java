package com.sysc4806project.services;

import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    private ShopRepository shopRepository;

    @Override
    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public void removeShopById(Long shopId) {
        shopRepository.deleteById(shopId);
    }

    @Override
    public List<Shop> getAllMerchantShops(Long userId) {
        return shopRepository.findByOwnerId(userId);
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<Shop> getShopById(Long shopId) {
        return shopRepository.findById(shopId);
    }
}

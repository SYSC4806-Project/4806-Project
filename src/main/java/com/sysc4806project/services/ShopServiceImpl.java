package com.sysc4806project.services;

import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopRepository shopRepository;

    @Override
    public void addOrUpdateShop(Shop shop) {
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


}

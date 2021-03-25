package com.sysc4806project.services;

import com.sysc4806project.models.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ShopService {
    public void addShop(Shop shop);
    public void removeShopById(Long shopId);
    public List<Shop> getAllMerchantShops(Long userId);
    public Optional<Shop> getShopById(Long shopId);
}

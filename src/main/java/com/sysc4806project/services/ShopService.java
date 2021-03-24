package com.sysc4806project.services;

import com.sysc4806project.models.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopService {
    public void addOrUpdateShop(Shop shop);
    public void removeShopById(Long shopId);
    public List<Shop> getAllMerchantShops(Long userId);
}

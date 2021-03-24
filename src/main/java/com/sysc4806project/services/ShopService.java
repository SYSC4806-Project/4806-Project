package com.sysc4806project.services;

import com.sysc4806project.models.Shop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShopService {
    public void addShop(Shop shop);
    //public void removeShop(Shop shop);
    public List<Shop> getAllMerchantShops(Long userId);
}

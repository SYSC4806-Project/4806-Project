package com.sysc4806project.services;

import com.sysc4806project.models.Shop;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShopService {
    public void addShop(Shop shop);
    public void removeShopById(Long shopId);
    public List<Shop> getAllMerchantShops(Long userId);
    public List<Shop> getAllShops();
    public Optional<Shop> getShopById(Long shopId);

    public List<Shop> findByName(String name);

    //@Query("select c from shop_category_list c where category_list like %?1% ")
    public List<Shop> findByCategory(String category_list);
}

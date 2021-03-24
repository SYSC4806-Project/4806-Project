package com.sysc4806project.services;

import com.sysc4806project.models.Shop;

import java.util.List;

public interface ShopService {
    public void addShop(Shop shop);
    //public void removeShop(Shop shop);
    public List<Shop> getAllShops();
}

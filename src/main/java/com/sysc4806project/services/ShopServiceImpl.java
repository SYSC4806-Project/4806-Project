package com.sysc4806project.services;

import com.sysc4806project.aop.GetExecutionTime;
import com.sysc4806project.aop.GetLogInfo;
import com.sysc4806project.models.Shop;
import com.sysc4806project.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements ShopService{
    @Autowired
    ShopRepository shopRepository;

    @Override
    @GetLogInfo
    public void addShop(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    @GetLogInfo
    public void removeShopById(Long shopId) {
        shopRepository.deleteById(shopId);
    }

    @Override
    public List<Shop> getAllMerchantShops(Long userId) {
        return shopRepository.findByOwnerId(userId);
    }

    @Override
    @GetExecutionTime
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<Shop> getShopById(Long shopId) {
        return shopRepository.findById(shopId);
    }

    @Override
    public List<Shop> findByName(String name) {
        return shopRepository.findByNameLike("%"+name+"%");
    }

    @Override
    public List<Shop> findByCategory(String category_list) {
        return shopRepository.findByCategoryList(category_list);

                //findByCategoryList("%" + category+ "%");
    }


}

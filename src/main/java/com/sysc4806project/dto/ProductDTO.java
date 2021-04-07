package com.sysc4806project.dto;

import com.sysc4806project.models.Shop;

import javax.persistence.*;

public class ProductDTO {
    private Long id;
    private String name;
    private Long parentShopId;
    private double price;
    private String description;
    private String imageName;
    private int inventoryNum;

    public ProductDTO(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentShopId() {
        return parentShopId;
    }

    public void setParentShopId(Long parentShopId) {
        this.parentShopId = parentShopId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

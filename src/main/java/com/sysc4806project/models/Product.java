package com.sysc4806project.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop parentShop;

    @Column(nullable = false)
    private double price;

    private String description;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private String imageName;

    @Column(nullable = false)
    private int inventoryNum;

    public Product() {}

    public Product(String name, Shop shop, double price, String description, String imageName, int inventoryNumber) {
        this.name = name;
        this.parentShop = shop;
        this.price = price;
        this.description = description;
        this.imageName = imageName;
        this.inventoryNum = inventoryNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shop getParentShop() {
        return parentShop;
    }

    public void setParentShop(Shop shop) {
        this.parentShop = shop;
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
}

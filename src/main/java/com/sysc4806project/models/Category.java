package com.sysc4806project.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

    private String name;

    @ManyToOne
    @JoinColumn(name = "shop_id", nullable = false)
    private Shop parentShop;

    public Category() {
    }

    public Category(String name, Shop parentShop) {
        this.name = name;
        this.parentShop = parentShop;
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

    public void setParentShop(Shop parentShop) {
        this.parentShop = parentShop;
    }
}

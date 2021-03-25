package com.sysc4806project.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shop")
public class Shop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ElementCollection
    private List<String> categoryList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    public Shop() {
    }

    public Shop(String name, User owner, List<String> categories) {
        this.name = name;
        this.owner = owner;
        this.categoryList = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categories) {
        this.categoryList = categories;
    }
}

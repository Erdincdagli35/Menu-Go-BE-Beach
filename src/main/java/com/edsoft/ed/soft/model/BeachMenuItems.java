package com.edsoft.ed.soft.model;

import java.math.BigDecimal;

public class BeachMenuItems {
    public BeachMenuItems() {}

    public BeachMenuItems(String name, String description, BigDecimal price, BeachCategory beachCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.beachCategory = beachCategory;
    }

    private String name;
    private String description;
    private BigDecimal price;
    private BeachCategory beachCategory;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BeachCategory getCategory() {
        return beachCategory;
    }

    public void setCategory(BeachCategory beachCategory) {
        this.beachCategory = beachCategory;
    }
}

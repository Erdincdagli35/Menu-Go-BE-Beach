package com.edsoft.ed.soft.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "items")
@Data
public class BeachMenuItems {

    public BeachMenuItems() {}

    public BeachMenuItems(Long id, BigDecimal price, List<MenuItemTranslation> translations, BeachCategory beachCategory) {
        Id = id;
        this.price = price;
        this.translations = translations;
        this.beachCategory = beachCategory;
    }

    public BeachMenuItems(BeachCategory beachCategory, List<MenuItemTranslation> translations, BigDecimal price) {
        this.beachCategory = beachCategory;
        this.translations = translations;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private BigDecimal price;

    @OneToMany(mappedBy = "menuItem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItemTranslation> translations;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BeachCategory beachCategory;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public BeachCategory getBeachCategory() {
        return beachCategory;
    }

    public void setBeachCategory(BeachCategory beachCategory) {
        this.beachCategory = beachCategory;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<MenuItemTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(List<MenuItemTranslation> translations) {
        this.translations = translations;
    }
}

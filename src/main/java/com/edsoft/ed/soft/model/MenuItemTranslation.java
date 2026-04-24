package com.edsoft.ed.soft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "items_translation")
public class MenuItemTranslation {

    public MenuItemTranslation() {
    }

    public MenuItemTranslation(String lang, String name, String description, BeachMenuItems menuItem) {
        this.lang = lang;
        this.name = name;
        this.description = description;
        this.menuItem = menuItem;
    }

    public MenuItemTranslation(Long id, String lang, String name, String description, BeachMenuItems menuItem) {
        this.id = id;
        this.lang = lang;
        this.name = name;
        this.description = description;
        this.menuItem = menuItem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lang; // TR / EN

    private String name;

    private String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "menu_item_id")
    private BeachMenuItems menuItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

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

    public BeachMenuItems getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(BeachMenuItems menuItem) {
        this.menuItem = menuItem;
    }
}

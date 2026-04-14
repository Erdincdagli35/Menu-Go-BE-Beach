package com.edsoft.ed.soft.service;

import com.edsoft.ed.soft.data.BeachMenu;
import com.edsoft.ed.soft.model.BeachMenuItems;
import com.edsoft.ed.soft.model.MenuItemTranslation;
import com.edsoft.ed.soft.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeachMenuService  {

    @Autowired
    MenuRepository menuRepository;

    public List<BeachMenu> listAll(String lang) {

        List<BeachMenuItems> items = menuRepository.findAll();

        return items.stream()
                .map(item -> {

                    MenuItemTranslation translation = item.getTranslations().stream()
                            .filter(t -> t.getLang().equalsIgnoreCase(lang))
                            .findFirst()
                            .orElseGet(() ->
                                    item.getTranslations().stream().findFirst().orElse(null)
                            );

                    return new BeachMenu(
                            item.getId(),
                            translation != null ? translation.getName() : "",
                            translation != null ? translation.getDescription() : "",
                            item.getPrice(),
                            item.getBeachCategory()
                    );
                })
                .toList();
    }

    public BeachMenuItems getById(Long id) {
        return menuRepository.findOneById(id);
    }

    public BeachMenuItems save(BeachMenuItems menuItem) {
        if (menuItem.getTranslations() != null) {
            menuItem.getTranslations().forEach(t -> t.setMenuItem(menuItem));
        }
        return menuRepository.save(menuItem);
    }

    public BeachMenuItems update(Long id, BeachMenuItems updatedItem) {
        BeachMenuItems existing = getById(id);

        existing.setPrice(updatedItem.getPrice());
        existing.setBeachCategory(updatedItem.getBeachCategory());

        // translation update (basit versiyon)
        existing.getTranslations().clear();

        if (updatedItem.getTranslations() != null) {
            updatedItem.getTranslations().forEach(t -> {
                t.setMenuItem(existing);
                existing.getTranslations().add(t);
            });
        }

        return menuRepository.save(existing);
    }

    public void delete(Long id) {
        menuRepository.deleteById(id);
    }
}
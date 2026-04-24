package com.edsoft.ed.soft.controller;

import com.edsoft.ed.soft.data.BeachMenu;
import com.edsoft.ed.soft.model.BeachMenuItems;
import com.edsoft.ed.soft.service.BeachMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = {"http://localhost:4200",
                        "https://terasotelmenugo-beach-test.up.railway.app"})
public class MenuController {

    @Autowired
    BeachMenuService menuService;

    @GetMapping("/beach")
    public ResponseEntity<List<BeachMenu>> getBeachMenu(
            @RequestParam(defaultValue = "TR") String lang) {

        return ResponseEntity.ok(menuService.listAll(lang));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BeachMenuItems> getById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BeachMenuItems> create(@RequestBody BeachMenuItems item) {
        return ResponseEntity.ok(menuService.save(item));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BeachMenuItems> update(@PathVariable Long id,
                                                @RequestBody BeachMenuItems item) {
        return ResponseEntity.ok(menuService.update(id, item));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

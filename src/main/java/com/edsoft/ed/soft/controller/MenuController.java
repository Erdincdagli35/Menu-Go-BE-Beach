package com.edsoft.ed.soft.controller;

import com.edsoft.ed.soft.data.BeachMenu;
import com.edsoft.ed.soft.service.BeachMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = {"http://localhost:4200",
                        "https://terasotelmenugo-beach.up.railway.app"})
public class MenuController {

    @Autowired
    BeachMenuService menuService;

    @GetMapping("/beach")
    public ResponseEntity<List<BeachMenu>> getBeachMenu(
            @RequestParam(defaultValue = "TR") String lang) {

        return ResponseEntity.ok(menuService.listAll(lang));
    }


}

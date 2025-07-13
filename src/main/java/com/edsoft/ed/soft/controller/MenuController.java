package com.edsoft.ed.soft.controller;

import com.edsoft.ed.soft.model.BeachCategory;
import com.edsoft.ed.soft.model.BeachMenuItems;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin(origins = {"http://localhost:4200",
                        "https://terasotelmenugo-beach.up.railway.app"})
public class MenuController {

    @GetMapping("/plaj")
    public ResponseEntity<List<BeachMenuItems>> getBeachMenu(@RequestParam(defaultValue = "TR") String lang) {
        List<BeachMenuItems> menuItems = null;

        if (lang.equalsIgnoreCase("TR")) {
            menuItems = List.of(
                    new BeachMenuItems("Çay", "Demleme siyah çay", new BigDecimal("50"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Türk Kahvesi", "Klasik Türk kahvesi", new BigDecimal("100"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Nescafe", "Hazır granül kahve", new BigDecimal("150"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Küçük Su", "330 ml su", new BigDecimal("50"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Büyük Su", "1 lt su", new BigDecimal("100"), BeachCategory.HotDrinks),

                    new BeachMenuItems("Soğuk Kahve Çeşitleri", "Buzlu kahve çeşitleri", new BigDecimal("200"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Soda", "Doğal maden suyu", new BigDecimal("80"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Ayran", "Yoğurttan yapılan geleneksel içecek", new BigDecimal("80"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Ev Yapımı Limonata", "Taze sıkılmış limonlu içecek", new BigDecimal("100"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Red Bull", "Enerji içeceği", new BigDecimal("150"), BeachCategory.ColdDrinks),

                    new BeachMenuItems("Tuborg", "Şişe bira", new BigDecimal("200"), BeachCategory.Beer),
                    new BeachMenuItems("Carlsberg", "Şişe bira", new BigDecimal("200"), BeachCategory.Beer),
                    new BeachMenuItems("Sol", "Meksika usulü bira", new BigDecimal("250"), BeachCategory.Beer),

                    new BeachMenuItems("Kaşarlı Tost", "Kaşar peyniri ile hazırlanan sıcak sandviç", new BigDecimal("200"), BeachCategory.Sandawichs),
                    new BeachMenuItems("Karışık Tost", "Sucuk, salam ve kaşar içeren sandviç", new BigDecimal("300"), BeachCategory.Sandawichs),

                    new BeachMenuItems("Gözleme Çeşitleri", "Peynirli, patatesli, ıspanaklı gözlemeler", new BigDecimal("300"), BeachCategory.Snacks),

                    new BeachMenuItems("Hamburger", "Izgara köfte ile klasik hamburger", new BigDecimal("450"), BeachCategory.Burgers),
                    new BeachMenuItems("Cheese Hamburger", "Kaşarlı hamburger", new BigDecimal("500"), BeachCategory.Burgers),

                    new BeachMenuItems("Patates", "Kızartılmış patates", new BigDecimal("200"), BeachCategory.Snacks),
                    new BeachMenuItems("Sigara Böreği", "Peynirli ince yufka böreği", new BigDecimal("200"), BeachCategory.Snacks),
                    new BeachMenuItems("Bira Tabağı", "Patates, çerez, kızartmalardan oluşan tabak", new BigDecimal("400"), BeachCategory.Snacks)
            );
        } else if (lang.equalsIgnoreCase("EN")) {
            menuItems = List.of(
                    new BeachMenuItems("Tea", "Brewed black tea", new BigDecimal("50"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Turkish Coffee", "Traditional Turkish coffee", new BigDecimal("100"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Nescafe", "Instant granulated coffee", new BigDecimal("150"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Small Water", "330 ml bottled water", new BigDecimal("50"), BeachCategory.HotDrinks),
                    new BeachMenuItems("Large Water", "1 liter bottled water", new BigDecimal("100"), BeachCategory.HotDrinks),

                    new BeachMenuItems("Iced Coffee Varieties", "Cold coffee drinks with ice", new BigDecimal("200"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Soda", "Sparkling mineral water", new BigDecimal("80"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Ayran", "Traditional yogurt-based beverage", new BigDecimal("80"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Homemade Lemonade", "Freshly squeezed lemon drink", new BigDecimal("100"), BeachCategory.ColdDrinks),
                    new BeachMenuItems("Red Bull", "Energy drink", new BigDecimal("150"), BeachCategory.ColdDrinks),

                    new BeachMenuItems("Tuborg", "Bottled beer", new BigDecimal("200"), BeachCategory.Beer),
                    new BeachMenuItems("Carlsberg", "Bottled beer", new BigDecimal("200"), BeachCategory.Beer),
                    new BeachMenuItems("Sol", "Mexican-style ale beer", new BigDecimal("250"), BeachCategory.Beer),

                    new BeachMenuItems("Toast with Cheese", "Hot sandwich with melted cheese", new BigDecimal("200"), BeachCategory.Sandawichs),
                    new BeachMenuItems("Mixed Toast", "Hot sandwich with sausage, salami and cheese", new BigDecimal("300"), BeachCategory.Sandawichs),

                    new BeachMenuItems("Stuffed Flatbreads", "Varieties filled with cheese, potato, or spinach", new BigDecimal("300"), BeachCategory.Snacks),

                    new BeachMenuItems("Hamburger", "Grilled beef patty in bun", new BigDecimal("450"), BeachCategory.Burgers),
                    new BeachMenuItems("Cheeseburger", "Hamburger with melted cheese", new BigDecimal("500"), BeachCategory.Burgers),

                    new BeachMenuItems("French Fries", "Deep-fried potato sticks", new BigDecimal("200"), BeachCategory.Snacks),
                    new BeachMenuItems("Cigar Pastry", "Fried pastry filled with cheese", new BigDecimal("200"), BeachCategory.Snacks),
                    new BeachMenuItems("Beer Plate", "Mixed snacks plate for beer", new BigDecimal("400"), BeachCategory.Snacks)
            );
        }

        return ResponseEntity.ok(menuItems);
    }

}

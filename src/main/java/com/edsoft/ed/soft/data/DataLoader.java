package com.edsoft.ed.soft.data;

import com.edsoft.ed.soft.model.BeachCategory;
import com.edsoft.ed.soft.model.BeachMenuItems;
import com.edsoft.ed.soft.model.MenuItemTranslation;
import com.edsoft.ed.soft.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader {

    @Autowired
    public MenuRepository itemRepository;

    @Bean
    public CommandLineRunner seed() {
        return args -> {

            if (itemRepository.count() == 0) {

                List<BeachMenuItems> items = List.of(

                        createItem("Çay", "Demleme siyah çay",
                                "Tea", "Brewed black tea",
                                "50", BeachCategory.HotDrinks),

                        createItem("Türk Kahvesi", "Klasik Türk kahvesi",
                                "Turkish Coffee", "Traditional Turkish coffee",
                                "100", BeachCategory.HotDrinks),

                        createItem("Nescafe", "Hazır granül kahve",
                                "Nescafe", "Instant granulated coffee",
                                "150", BeachCategory.HotDrinks),

                        createItem("Küçük Su", "330 ml su",
                                "Small Water", "330 ml bottled water",
                                "50", BeachCategory.HotDrinks),

                        createItem("Büyük Su", "1 lt su",
                                "Large Water", "1 liter bottled water",
                                "100", BeachCategory.HotDrinks),

                        createItem("Soğuk Kahve Çeşitleri", "Buzlu kahve çeşitleri",
                                "Iced Coffee Varieties", "Cold coffee drinks with ice",
                                "200", BeachCategory.ColdDrinks),

                        createItem("Soda", "Doğal maden suyu",
                                "Soda", "Sparkling mineral water",
                                "100", BeachCategory.ColdDrinks),

                        createItem("Ayran", "Yoğurttan yapılan geleneksel içecek",
                                "Ayran", "Traditional yogurt-based beverage",
                                "100", BeachCategory.ColdDrinks),

                        createItem("Soğuk İçecekler", "Soğuk içecek çeşitleri",
                                "Cold Drinks", "Cold drink varieties",
                                "150", BeachCategory.ColdDrinks),

                        createItem("Ev Yapımı Limonata", "Taze sıkılmış limonlu içecek",
                                "Homemade Lemonade", "Freshly squeezed lemon drink",
                                "100", BeachCategory.ColdDrinks),

                        createItem("Red Bull", "Enerji içeceği",
                                "Red Bull", "Energy drink",
                                "150", BeachCategory.ColdDrinks),

                        createItem("Tuborg", "Şişe bira (50cl)",
                                "Tuborg", "Bottled beer (50cl)",
                                "250", BeachCategory.Beer),

                        createItem("Carlsberg", "Şişe bira (50cl)",
                                "Carlsberg", "Bottled beer (50cl)",
                                "250", BeachCategory.Beer),

                        createItem("Sol", "Meksika usulü bira (33cl)",
                                "Sol", "Mexican-style ale beer (33cl)",
                                "250", BeachCategory.Beer),

                        createItem("Kaşarlı Tost", "Kaşar peyniri ile hazırlanan sıcak sandviç",
                                "Toast with Cheese", "Hot sandwich with melted cheese",
                                "200", BeachCategory.Sandawichs),

                        createItem("Karışık Tost", "Sucuk, salam ve kaşar içeren sandviç",
                                "Mixed Toast", "Hot sandwich with sausage, salami and cheese",
                                "300", BeachCategory.Sandawichs),

                        createItem("Gözleme Çeşitleri", "Peynirli, patatesli, ıspanaklı gözlemeler",
                                "Stuffed Flatbreads", "Varieties filled with cheese, potato, or spinach",
                                "300", BeachCategory.Snacks),

                        createItem("Hamburger", "Izgara köfte ile klasik hamburger",
                                "Hamburger", "Grilled beef patty in bun",
                                "450", BeachCategory.Burgers),

                        createItem("Cheese Hamburger", "Cheddar peynirli hamburger",
                                "Cheeseburger", "Hamburger with melted cheddar",
                                "500", BeachCategory.Burgers),

                        createItem("Patates", "Kızartılmış patates",
                                "French Fries", "Deep-fried potato sticks",
                                "200", BeachCategory.Snacks),

                        createItem("Sigara Böreği", "Peynirli ince yufka böreği",
                                "Cigar Pastry", "Fried pastry filled with cheese",
                                "200", BeachCategory.Snacks),

                        createItem("Bira Tabağı", "Patates, çerez, kızartmalardan oluşan tabak",
                                "Beer Plate", "Mixed snacks plate for beer",
                                "400", BeachCategory.Snacks)
                );

                itemRepository.saveAll(items);
            }
        };
    }

    private BeachMenuItems createItem(
            String trName,
            String trDesc,
            String enName,
            String enDesc,
            String price,
            BeachCategory category
    ) {
        BeachMenuItems item = new BeachMenuItems();
        item.setPrice(new BigDecimal(price));
        item.setBeachCategory(category);

        item.getTranslations().add(new MenuItemTranslation(
                "TR", trName, trDesc, item
        ));

        item.getTranslations().add(new MenuItemTranslation(
                "EN", enName, enDesc, item
        ));

        return item;
    }
}

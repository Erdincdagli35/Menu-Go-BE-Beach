package com.edsoft.ed.soft.service;

import com.edsoft.ed.soft.data.BeachMenu;
import com.edsoft.ed.soft.model.BeachMenuItems;
import com.edsoft.ed.soft.model.MenuItemTranslation;
import com.edsoft.ed.soft.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class BeachMenuService {

    @Autowired
    MenuRepository menuRepository;

    // Aldığımız bilgiler (Güvenlik için bunları config dosyasından çekmek daha iyidir)
    private static final String TELEGRAM_TOKEN = "8793995144:AAHdC4lPxuuVplXbqpQcYAAdbY5fV7KC87s";
    private static final String CHAT_ID = "8416899324";

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

    // CREATE işlemi burada yapılıyor
    public BeachMenuItems save(BeachMenuItems menuItem) {
        if (menuItem.getTranslations() != null) {
            menuItem.getTranslations().forEach(t -> t.setMenuItem(menuItem));
        }
        
        BeachMenuItems savedItem = menuRepository.save(menuItem);

        // Kayıt başarılı olduktan sonra bildirim gönderiyoruz
        String mesaj = "🚀 Yeni Menü Öğesi Eklendi!\n" +
                       "İsim: " + (savedItem.getTranslations().isEmpty() ? "İsimsiz" : savedItem.getTranslations().get(0).getName()) + "\n" +
                       "Fiyat: " + savedItem.getPrice();
        
        sendTelegramNotification(mesaj);

        return savedItem;
    }

    public BeachMenuItems update(Long id, BeachMenuItems updatedItem) {
        BeachMenuItems existing = getById(id);

        existing.setPrice(updatedItem.getPrice());
        existing.setBeachCategory(updatedItem.getBeachCategory());

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

    // Telegram'a istek atan yardımcı metod
    private void sendTelegramNotification(String messageText) {
        // Ayrı bir thread'de çalıştırmak uygulamanın hızını yavaşlatmaz (Opsiyonel ama önerilir)
        new Thread(() -> {
            try {
                String encodedMessage = URLEncoder.encode(messageText, StandardCharsets.UTF_8.toString());
                String urlString = String.format(
                    "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s",
                    TELEGRAM_TOKEN, CHAT_ID, encodedMessage
                );

                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                
                // İsteği gerçekleştir
                conn.getResponseCode(); 
                conn.disconnect();
            } catch (Exception e) {
                System.err.println("Telegram bildirimi gönderilemedi: " + e.getMessage());
            }
        }).start();
    }
}

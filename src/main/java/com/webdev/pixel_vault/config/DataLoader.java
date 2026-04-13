package com.webdev.pixel_vault.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.webdev.pixel_vault.models.Product;
import com.webdev.pixel_vault.models.User;
import com.webdev.pixel_vault.repository.ProductRepository;
import com.webdev.pixel_vault.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final ProductRepository productRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepo, ProductRepository productRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.productRepo = productRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ADMIN");
            userRepo.save(admin);
        }
        if (userRepo.findByUsername("staff1").isEmpty()) {
            User staff1 = new User();
            staff1.setUsername("staff1");
            staff1.setPassword(passwordEncoder.encode("staff123"));
            staff1.setRole("STAFF");
            userRepo.save(staff1);
        }
        if (userRepo.findByUsername("staff2").isEmpty()) {
            User staff2 = new User();
            staff2.setUsername("staff2");
            staff2.setPassword(passwordEncoder.encode("staff456"));
            staff2.setRole("STAFF");
            userRepo.save(staff2);
        }

        if (productRepo.count() == 0) {
            productRepo.saveAll(List.of(
                new Product(0, "Pixel Mouse",                 "High precision gaming mouse",                      49.99,  "Electronics", 120, null),
                new Product(0, "Pixel Keyboard",              "Mechanical keyboard with RGB lighting",             89.99,  "Electronics",  75, null),
                new Product(0, "Wireless Earbuds",            "Bluetooth earbuds with noise cancellation",         59.99,  "Electronics", 150, null),
                new Product(0, "Gaming Headset",              "Surround sound headset with mic",                  79.99,  "Electronics",  80, null),
                new Product(0, "USB-C Hub",                   "Multiport adapter with HDMI and USB 3.0",           34.99,  "Electronics", 100, null),
                new Product(0, "External SSD 1TB",            "Portable solid state drive for fast storage",      129.99,  "Electronics",  60, null),
                new Product(0, "Webcam HD",                   "1080p webcam for streaming and meetings",           49.99,  "Electronics",  90, null),
                new Product(0, "Mechanical Keyboard",         "Customizable RGB mechanical keyboard",              99.99,  "Electronics",  50, null),
                new Product(0, "Gaming Mouse Pad",            "Extended mouse pad with non-slip base",            19.99,  "Electronics", 200, null),
                new Product(0, "Smartphone Stand",            "Adjustable phone stand for desk",                  14.99,  "Electronics", 180, null),
                new Product(0, "Laptop Sleeve",               "Protective sleeve for 13-inch laptops",            24.99,  "Accessories", 120, null),
                new Product(0, "Charging Dock",               "Wireless charging dock for multiple devices",       39.99,  "Accessories",  75, null),
                new Product(0, "Portable Charger",            "10000mAh power bank for phones",                   29.99,  "Accessories", 200, null),
                new Product(0, "Bluetooth Speaker",           "Compact speaker with powerful sound",              49.99,  "Electronics", 150, null),
                new Product(0, "Smartwatch",                  "Fitness tracking smartwatch with notifications",   199.99,  "Electronics",  80, null),
                new Product(0, "HDMI Cable",                  "6ft high-speed HDMI cable",                        12.99,  "Accessories", 300, null),
                new Product(0, "USB Flash Drive 128GB",       "High-speed portable USB storage",                  24.99,  "Accessories", 250, null),
                new Product(0, "Laptop Cooling Pad",          "USB cooling pad for laptops",                      29.99,  "Accessories",  90, null),
                new Product(0, "Mechanical Gaming Keypad",    "One-handed keypad for gaming",                     59.99,  "Electronics",  40, null),
                new Product(0, "Noise Cancelling Headphones", "Over-ear headphones with ANC",                    129.99,  "Electronics",  70, null)
            ));
        }
    }
}

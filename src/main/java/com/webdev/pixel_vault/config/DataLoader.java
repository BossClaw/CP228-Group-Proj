package com.webdev.pixel_vault.config;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.webdev.pixel_vault.models.User;
import com.webdev.pixel_vault.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
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
    }
}

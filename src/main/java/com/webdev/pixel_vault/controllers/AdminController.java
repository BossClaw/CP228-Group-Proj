package com.webdev.pixel_vault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.webdev.pixel_vault.models.User;
import com.webdev.pixel_vault.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String adminDashboard(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "Admin";
    }

    @PostMapping("/toggle-role/{id}")
    public String toggleRole(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        if (!user.getRole().equals("ADMIN")) {
            user.setRole(user.getRole().equals("CUSTOMER") ? "STAFF" : "CUSTOMER");
            userRepository.save(user);
        }
        return "redirect:/admin";
    }
}

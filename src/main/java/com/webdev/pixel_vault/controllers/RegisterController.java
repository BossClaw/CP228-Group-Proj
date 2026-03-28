package com.webdev.pixel_vault.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.webdev.pixel_vault.models.User;
import com.webdev.pixel_vault.repository.UserRepository;

@Controller
public class RegisterController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "Register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (user.getUsername() == null || user.getUsername().isBlank()) {
            model.addAttribute("error", "Username is required.");
            return "Register";
        }

        if (user.getPassword() == null || user.getPassword().length() < 6) {
            model.addAttribute("error", "Password must be at least 6 characters.");
            return "Register";
        }

        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("error", "Username is already taken.");
            return "Register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("CUSTOMER");
        userRepository.save(user);

        return "redirect:/login?registered";
    }
}

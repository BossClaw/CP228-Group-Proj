package com.webdev.pixel_vault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.webdev.pixel_vault.models.Product;
import com.webdev.pixel_vault.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class CreateProductController {

    private final ProductService productService;

    // INJECT SERVICE
    public CreateProductController(ProductService productService) {
        this.productService = productService;
    }

    // SHOW EMPTY FORM
    @GetMapping("/create-product")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "CreateProduct";
    }

    // HANDLE FORM SUBMISSION
    @PostMapping("/create-product")
    public String createProduct(@Valid Product product, BindingResult result) {

        // VALIDATION FAILED — RETURN TO FORM WITH ERRORS
        if (result.hasErrors()) {
            return "CreateProduct";
        }

        // SAVE AND REDIRECT TO PRODUCT LIST
        productService.addProduct(product);
        return "redirect:/products";
    }
}

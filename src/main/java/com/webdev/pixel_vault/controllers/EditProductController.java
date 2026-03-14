package com.webdev.pixel_vault.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.webdev.pixel_vault.models.Product;
import com.webdev.pixel_vault.service.ProductService;

import jakarta.validation.Valid;

@Controller
public class EditProductController {

    private final ProductService productService;

    // INJECT SERVICE
    public EditProductController(ProductService productService) {
        this.productService = productService;
    }

    // LOAD EXISTING PRODUCT INTO FORM
    @GetMapping("/edit-product/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);

        // GUARD — REDIRECT IF PRODUCT NOT FOUND
        if (product == null) {
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        return "EditProduct";
    }

    // HANDLE FORM SUBMISSION
    @PostMapping("/edit-product/{id}")
    public String editProduct(@PathVariable int id, @Valid Product product, BindingResult result) {

        // VALIDATION FAILED — RETURN TO FORM WITH ERRORS
        if (result.hasErrors()) {
            return "EditProduct";
        }

        // PRESERVE ORIGINAL ID AND SAVE
        product.setId(id);
        productService.addProduct(product);
        return "redirect:/products";
    }
}

package com.webdev.pixel_vault.controllers;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.webdev.pixel_vault.models.Product;
import com.webdev.pixel_vault.service.ProductService;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getProducts(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sortBy,
            @RequestParam(name = "direction", required = false, defaultValue = "ASC") String direction,
            Model model){


        Sort.Direction sortedDirection = direction.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(sortedDirection,sortBy);

        Pageable pageable = PageRequest.of(page,size,sort);


        Page<Product> productPage;

        if (search != null && !search.isEmpty()) {
            try {
                int searchId = Integer.parseInt(search.trim());
                Product found = productService.getProductById(searchId);
                List<Product> result = found != null ? List.of(found) : List.of();
                productPage = new PageImpl<>(result, pageable, result.size());
            } catch (NumberFormatException e) {
                productPage = productService.findProductByName(search, pageable);
            }
        } else {
            productPage = productService.getAllProductsPageable(pageable);
        }

        model.addAttribute("products", productPage.getContent());
        model.addAttribute("total", productPage.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("search", search);
        model.addAttribute("sort", sortBy);
        model.addAttribute("direction", direction);
        //Switching the Pagination
        model.addAttribute("hasPrevious", productPage.hasPrevious());
        model.addAttribute("hasNext", productPage.hasNext());
        model.addAttribute("startIndex", page * size +1);
        model.addAttribute("endIndex", Math.min((page+1)*size,(int)productPage.getTotalElements()));

        return "Products";
    }
}

package com.webdev.pixel_vault.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.webdev.pixel_vault.models.Product;
import com.webdev.pixel_vault.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findProductByName(String name, Pageable page){
        return productRepository.findByNameContainingIgnoringCase(name,page);
    }

    public Page<Product> getProductByIdContaining(int id, Pageable pageable){
        return productRepository.findByIdContaining(id, pageable);
    }

    public boolean existsProduct(int id) {
        return productRepository.existsById(id);
    }

    public long countProducts() {
        return productRepository.count();
    }

    
}

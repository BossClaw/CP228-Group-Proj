package com.webdev.pixel_vault.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdev.pixel_vault.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findByNameContainingIgnoringCase(String name, Pageable pageable);

    Page<Product> findByIdContaining(int id, Pageable pageable);

}

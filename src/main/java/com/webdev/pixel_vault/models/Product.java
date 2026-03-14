package com.webdev.pixel_vault.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @NotBlank(message="Product Name Required")
    private String name;

    @NotBlank(message="Description required")
    private String description;

    @NotNull(message="Price required")
    @DecimalMin(value="0.0", inclusive=false, message="Price must be more than 0")
    private Double price;

    @NotBlank(message="Category required")
    private String category;

    @NotNull(message="Quantity required")
    @Min(value=0, message="Cannot be negative")
    private int inStock;

    // AUTO-SET ON INSERT, NEVER MODIFIED
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

}

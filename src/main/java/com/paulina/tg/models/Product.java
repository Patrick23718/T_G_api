package com.paulina.tg.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private int secure_inventory;
    private String description;
    private boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Product(
            String name,
            String brand,
            BigDecimal price,
            int inventory,
            int secure_inventory,
            String description,
            boolean active,
            Category category
    ) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.inventory = inventory;
        this.secure_inventory = secure_inventory;
        this.description = description;
        this.active = active;
        this.category = category;
    }
}

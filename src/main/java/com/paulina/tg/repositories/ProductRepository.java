package com.paulina.tg.repositories;

import com.paulina.tg.models.Category;
import com.paulina.tg.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByActiveIsAndNameContainingIgnoreCaseAndBrandContainingIgnoreCase(boolean active, String name, String brand);
    List<Product> findByCategory(Category category);
}

package com.paulina.tg.repositories;

import com.paulina.tg.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    List<Category> findByActiveIsAndNameContainingIgnoreCase(boolean active, String name);
    Category findByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);
}

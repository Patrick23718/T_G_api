package com.paulina.tg.services.category;

import com.paulina.tg.exceptions.AlreadyExistException;
import com.paulina.tg.exceptions.RessourceNotFoundException;
import com.paulina.tg.models.Category;
import com.paulina.tg.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategoriesByActiveAndName(boolean active, String name) {
        return categoryRepository.findByActiveIsAndNameContainingIgnoreCase(active, name);
    }

    @Override
    public Category findCategoryById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Category with id: " + id + " not found"));
    }

    @Override
    public Category findCategoryByName(String name) {
        return categoryRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Category saveCategory(Category category) {
        return  Optional.of(category).filter(c -> !categoryRepository.existsByNameIgnoreCase(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistException(category.getName()+" already exists"));
    }

    @Override
    public Category updateCategoryName(String name, String categoryId) {
        return Optional.ofNullable(findCategoryById(categoryId)).map(oldCategory -> {
            oldCategory.setName(name);
            return categoryRepository.save(oldCategory);
        }) .orElseThrow(()-> new RessourceNotFoundException("Category not found!"));
    }

    @Override
    public void deleteCategoryById(String id) {
        Category category = findCategoryById(id);
        if (category.getProducts().isEmpty()) {
            categoryRepository.deleteById(id);
        }
    }

    @Override
    public void activateCategory(String categoryId) {
        Optional.ofNullable(findCategoryById(categoryId)).map(oldCategory -> {
            oldCategory.setActive(true);
            return categoryRepository.save(oldCategory);
        }) .orElseThrow(()-> new RessourceNotFoundException("Category not found!"));

    }

    @Override
    public void deactivateCategory(String categoryId) {
        Optional.ofNullable(findCategoryById(categoryId)).map(oldCategory -> {
            oldCategory.setActive(false);
            return categoryRepository.save(oldCategory);
        }) .orElseThrow(()-> new RessourceNotFoundException("Category not found!"));
    }
}

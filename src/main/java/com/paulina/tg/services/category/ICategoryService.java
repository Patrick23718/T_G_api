package com.paulina.tg.services.category;

import com.paulina.tg.models.Category;
import com.paulina.tg.request.CategoryRequest;

import java.util.List;

public interface ICategoryService {
    List<Category> findAllCategoriesByActiveAndName(boolean active, String name);
    Category findCategoryById(String id);
    Category findCategoryByName(String name);
    Category saveCategory(Category category);
    Category updateCategoryName(String name, String categoryId);
    void deleteCategoryById(String id);
    void activateCategory(String categoryId);
    void deactivateCategory(String categoryId);
}

package org.project.spring_mini_project.features.category;

import org.project.spring_mini_project.features.category.dto.CategoryRequest;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> findAllCategories(int page, int size);
    List<CategoryResponse> findParentCategories();
    CategoryResponse findCategoryByAlias(String alias);
    CategoryResponse updateCategory(String alias, CategoryRequest categoryRequest);
    CategoryResponse disableCategory(String alias);
    CategoryResponse enableCategory(String alias);
}

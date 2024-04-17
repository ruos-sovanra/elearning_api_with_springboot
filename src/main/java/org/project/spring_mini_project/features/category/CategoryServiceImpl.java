package org.project.spring_mini_project.features.category;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Categories;
import org.project.spring_mini_project.features.category.dto.CategoryRequest;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;
import org.project.spring_mini_project.mapper.CategoryMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        // map the request to the domain
        Categories category = categoryMapper.toDomain(categoryRequest);

        // check if the category has a parent category
        if (categoryRequest.parentCategoryId() != null && categoryRequest.parentCategoryId() != 0) {
            // fetch the parent category from the database
            Optional<Categories> parentCategoryOptional = categoryRepository.findById(categoryRequest.parentCategoryId().longValue());

            // set the parent category if it exists
            if (parentCategoryOptional.isPresent()) {
                Categories parentCategory = parentCategoryOptional.get();
                category.setParentCategory(parentCategory);
            }
        } else {
            category.setParentCategory(null);
        }

        // save the new category
        category = categoryRepository.save(category);

        // map the domain to the response and return
        return categoryMapper.responseToCategory(category);
    }

    @Override
    public List<CategoryResponse> findAllCategories(int page, int size) {
        return categoryRepository.findAll(PageRequest.of(page, size)).stream()
                .map(categoryMapper::responseToCategory)
                .collect(Collectors.toList());
    }

    @Override
    public List<CategoryResponse> findParentCategories() {
        // Fetch all categories
        List<Categories> allCategories = categoryRepository.findAll();

        // Filter out categories that have children
        List<Categories> parentCategories = allCategories.stream()
                .filter(category -> !category.getSubCategories().isEmpty())
                .collect(Collectors.toList());

        // Convert to CategoryResponse and return
        return parentCategories.stream()
                .map(categoryMapper::responseToCategory)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findCategoryByAlias(String alias) {
        // Find the category by alias
        Categories category = categoryRepository.findCategoriesByAlias(alias);

        // Check if the category is null and throw an exception if it is
        if (category == null) {
            throw new NoSuchElementException("Category not found");
        }

        // Convert to CategoryResponse and return
        return categoryMapper.responseToCategory(category);
    }

    @Override
    public CategoryResponse updateCategory(String alias, CategoryRequest categoryRequest) {
        // Find the category by alias
        Categories category = categoryRepository.findCategoriesByAlias(alias);

        // Update the category with the new data
        categoryMapper.updateCategoryFromRequest(category, categoryRequest);

        // Save the updated category
        category = categoryRepository.save(category);

        // Convert to CategoryResponse and return
        return categoryMapper.responseToCategory(category);
    }

    @Override
    public CategoryResponse disableCategory(String alias) {
    int affectedRows = categoryRepository.updateIsDeletedStatusByAlias(alias, true);
    if (affectedRows == 0) {
        throw new NoSuchElementException("Category not found");
    }
    return findCategoryByAlias(alias);
    }

    @Override
    public CategoryResponse enableCategory(String alias) {
    int affectedRows = categoryRepository.updateIsDeletedStatusByAlias(alias, false);
    if (affectedRows == 0) {
        throw new NoSuchElementException("Category not found");
    }
    return findCategoryByAlias(alias);
    }
}
package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.project.spring_mini_project.domain.Categories;
import org.project.spring_mini_project.features.category.dto.CategoryRequest;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponse responseToCategory(Categories category);

    @Mapping(source = "parentCategoryId", target = "parentCategory.id")
    Categories toDomain(CategoryRequest request);

    @Mapping(source = "parentCategory.id", target = "parentCategoryId")
    CategoryRequest toDto(Categories category);

    void updateCategoryFromRequest(@MappingTarget Categories category, CategoryRequest categoryRequest);
}
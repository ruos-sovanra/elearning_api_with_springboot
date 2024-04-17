package org.project.spring_mini_project.features.category.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(Long id, String name,String alias,String icon, CategoryParentResponse parentCategory) {
}

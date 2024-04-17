package org.project.spring_mini_project.features.category.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(
        @NotBlank
        String name,
        @NotBlank
        String alias,
        String icon,
        @Nullable Integer parentCategoryId) {
}
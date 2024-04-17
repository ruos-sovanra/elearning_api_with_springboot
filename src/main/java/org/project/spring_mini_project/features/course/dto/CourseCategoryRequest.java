package org.project.spring_mini_project.features.course.dto;

import lombok.Builder;

import java.util.List;
@Builder
public record CourseCategoryRequest(Long category_id) {
}
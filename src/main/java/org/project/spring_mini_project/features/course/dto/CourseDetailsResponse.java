package org.project.spring_mini_project.features.course.dto;

import lombok.Builder;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;


@Builder
public record CourseDetailsResponse(Long id, String title, String description, String alias, String thumbnail, boolean is_free, String instructorGivenName, String categoryName) {
}
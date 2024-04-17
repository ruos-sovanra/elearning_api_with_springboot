package org.project.spring_mini_project.features.course.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

import java.util.List;

@Builder
public record CourseCreateRequest(@NotEmpty String title, String description, @NotEmpty String alias, String thumbnail, boolean is_free,@NotEmpty Long instructor_id,@NotEmpty Long Category_id) {
}
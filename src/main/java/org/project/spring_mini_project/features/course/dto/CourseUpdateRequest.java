package org.project.spring_mini_project.features.course.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record CourseUpdateRequest(String title, String description, String alias, String thumbnail, boolean is_free, Long instructor_id, List<Long> category_ids) {
}
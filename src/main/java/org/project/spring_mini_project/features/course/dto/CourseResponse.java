package org.project.spring_mini_project.features.course.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record CourseResponse(Long id, String title, String description,String alias,String thumbnail,boolean is_deleted,boolean is_free) {
}
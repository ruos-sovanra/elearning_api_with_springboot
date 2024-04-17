package org.project.spring_mini_project.features.enrollment.dto;

import lombok.Builder;

@Builder
public record EnrollmentProgressResponse(
        Long id,
        Integer progress
) {
}

package org.project.spring_mini_project.features.enrollment.dto;

import jakarta.validation.constraints.NotEmpty;

public record EnrollmentRequest(
        @NotEmpty
        String code,
        @NotEmpty
        Long course_id,
        @NotEmpty
        Long student_id) {
}

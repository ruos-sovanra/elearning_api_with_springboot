package org.project.spring_mini_project.features.student.dto;

public record StudentUpdateRequest(
        String high_school,
        String university,
        Long user_id
) {
}

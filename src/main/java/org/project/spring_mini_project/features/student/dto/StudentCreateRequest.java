package org.project.spring_mini_project.features.student.dto;

import jakarta.validation.constraints.NotEmpty;

public record StudentCreateRequest (
        String high_school,
        String university,
        @NotEmpty
        Long user_id

) {
}

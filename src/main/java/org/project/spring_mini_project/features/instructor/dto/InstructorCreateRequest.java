package org.project.spring_mini_project.features.instructor.dto;

import lombok.Builder;

@Builder
public record InstructorCreateRequest(
        String text,
        String family_name,
        String github,
        String given_name,
        String job_title,
        String linked_in,
        String national_id_card,
        String profile,
        String website,
        Long userId
) {}

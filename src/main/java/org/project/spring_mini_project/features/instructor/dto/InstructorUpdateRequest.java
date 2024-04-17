package org.project.spring_mini_project.features.instructor.dto;

import lombok.Builder;
import org.project.spring_mini_project.features.user.dto.UserRequest;

@Builder
public record InstructorUpdateRequest(
        String text,
        String family_name,
        String github,
        String given_name,
        Boolean is_blocked,
        String job_title,
        String linked_in,
        String profile,
        String website
) {}
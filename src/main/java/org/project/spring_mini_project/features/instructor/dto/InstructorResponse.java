package org.project.spring_mini_project.features.instructor.dto;

import lombok.Builder;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;

import java.util.Set;

@Builder
public record InstructorResponse(
        Integer id,
        String text,
        String family_name,
        String github,
        String given_name,
        Boolean is_blocked,
        Boolean is_deleted,
        String job_title,
        String linked_in,
        String national_id_card,
        String profile,
        String website,
        Long userId,
        UserDetailsResponse user
) {}

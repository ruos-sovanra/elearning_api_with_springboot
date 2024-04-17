package org.project.spring_mini_project.features.role.dto;

import lombok.Builder;

@Builder
public record RoleAuthorityResponse(Long id, String name) {
}

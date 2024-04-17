package org.project.spring_mini_project.features.role.dto;

import lombok.Builder;

import java.util.Set;
@Builder
public record RoleResponse(Long id, String name, Set<RoleAuthorityResponse> authorities) {
}
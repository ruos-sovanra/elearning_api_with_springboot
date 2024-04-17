package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.spring_mini_project.domain.Authority;
import org.project.spring_mini_project.domain.Role;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.role.dto.RoleAuthorityResponse;
import org.project.spring_mini_project.features.role.dto.RoleResponse;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(source = "authorities", target = "authorities", qualifiedByName = "mapAuthoritySetToRoleAuthorityResponseSet")
    RoleResponse roleToRoleResponse(Role role);

    @Named("mapAuthoritySetToRoleAuthorityResponseSet")
    default Set<RoleAuthorityResponse> mapAuthoritySetToRoleAuthorityResponseSet(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new RoleAuthorityResponse(authority.getId(), authority.getName()))
                .collect(Collectors.toSet());
    }
}

package org.project.spring_mini_project.features.role;

import org.project.spring_mini_project.features.role.dto.RoleAuthorityResponse;
import org.project.spring_mini_project.features.role.dto.RoleResponse;

import java.util.List;

public interface RoleService {
    List<RoleResponse> getAllRoles();
    RoleResponse getRoleByName(String name);
}

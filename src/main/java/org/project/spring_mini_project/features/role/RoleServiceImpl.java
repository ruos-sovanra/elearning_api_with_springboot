package org.project.spring_mini_project.features.role;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Role;
import org.project.spring_mini_project.features.role.dto.RoleAuthorityResponse;
import org.project.spring_mini_project.features.role.dto.RoleResponse;
import org.project.spring_mini_project.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::roleToRoleResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponse getRoleByName(String name) {
        Role role = roleRepository.findByName(name)
                .orElseThrow(() -> new NoSuchElementException("Role not found with name: " + name));
        return roleMapper.roleToRoleResponse(role);
    }
}

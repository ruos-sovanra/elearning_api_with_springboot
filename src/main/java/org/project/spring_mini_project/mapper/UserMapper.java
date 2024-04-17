package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;
import org.project.spring_mini_project.features.user.dto.UserRequest;

@Mapper(componentModel = "spring", uses = RoleMapper.class)
public interface UserMapper {
    @Mapping(source = "roles", target = "roles")
    @Mapping(source = "city.name", target = "city_name")
    @Mapping(source = "country.name", target = "country_name")
    UserDetailsResponse userToUserDetailsResponse(User user);

    User userRequestToUser(UserRequest userRequest);
}
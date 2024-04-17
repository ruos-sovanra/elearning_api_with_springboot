package org.project.spring_mini_project.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.project.spring_mini_project.domain.Instructor;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.instructor.dto.InstructorCreateRequest;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;
import org.project.spring_mini_project.features.instructor.dto.InstructorUpdateRequest;
import org.project.spring_mini_project.features.role.dto.RoleAuthorityResponse;
import org.project.spring_mini_project.features.role.dto.RoleResponse;
import org.project.spring_mini_project.features.user.UserRepository;
import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;

import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface InstructorMapper {

    @Mapping(source = "user", target = "userId", qualifiedByName = "mapUserToUserId")
    InstructorResponse instructorToInstructorResponse(Instructor instructor);

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    Instructor instructorCreateRequestToInstructor(InstructorCreateRequest instructorCreateRequest, @Context UserRepository userRepository);

    @Named("mapUserToUserId")
    default Long mapUserToUserId(User user) {
        return user != null ? user.getId() : null;
    }

    @Named("mapUserIdToUser")
    default User mapUserIdToUser(Long userId, @Context UserRepository userRepository) {
        if (userId == null) {
            return null;
        }

        return userRepository.findById(userId).orElse(null);
    }
}
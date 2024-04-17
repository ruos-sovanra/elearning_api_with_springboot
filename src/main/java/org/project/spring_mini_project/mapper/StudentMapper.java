package org.project.spring_mini_project.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.student.dto.StudentCreateRequest;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.project.spring_mini_project.features.student.dto.StudentUpdateRequest;
import org.project.spring_mini_project.features.user.UserRepository;

@Mapper(componentModel = "spring")
public interface StudentMapper {

//    @Mapping(source = "user", target = "user_id", qualifiedByName = "mapUserToUserId")

    StudentRespone mapStudentToStudentResponse(Student student);

    Student mapStudentRequestToStudent(StudentCreateRequest studentCreateRequest);

    Student mapStudentRequestToStudent(StudentUpdateRequest studentUpdateRequest);

//    @Named("mapUserToUserId")
//    default Long mapUserToUserId(User user) {
//        return user != null ? user.getId() : null;
//    }
//
//    @Named("mapUserIdToUser")
//
//    default User mapUserIdToUser(Long userId, @Context UserRepository userRepository) {
//        if (userId == null) {
//            return null;
//        }
//
//        return userRepository.findById(userId).orElse(null);
//    }
}

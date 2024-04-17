package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Enrollment;
import org.project.spring_mini_project.domain.User;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressResponse;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentResponse;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {
    @Mapping(target = "courseTitle", source = "course.title")
    @Mapping(target = "courseCategory", source = "course.categories.name") // changed to 'course.categories.name'
    @Mapping(target = "studentName", source = "student.user.username")
    EnrollmentResponse toEnrollmentResponse(Enrollment enrollment);
    EnrollmentRequest toEnrollmentRequest(Enrollment enrollment);

    EnrollmentProgressResponse toEnrollmentProgressResponse(Enrollment enrollment);

    Enrollment toEnrollmentProgress(EnrollmentProgressRequest enrollmentProgressRequest);

    Enrollment toEnrollment(EnrollmentRequest enrollmentRequest);

    @Named("mapCourseToString")
    default String mapCourseToString(Course course) {
        return course.getTitle();
    }

    @Named("mapUserToString")
    default String mapUserToString(User user) {
        return user.getGiven_name();
    }
}

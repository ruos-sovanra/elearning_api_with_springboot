package org.project.spring_mini_project.features.enrollment.dto;

import lombok.Builder;
import org.project.spring_mini_project.features.course.dto.CourseResponse;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
@Builder
public record EnrollmentDetailsResponse(
        Long id,
        String code,
        Boolean is_deleted,
        Boolean is_certified,
        Integer progress,
        String course_name,
        String student_name,
        String high_school,
        Boolean is_blocked,
        String university,
        CourseResponse course,
        StudentRespone student
){
}

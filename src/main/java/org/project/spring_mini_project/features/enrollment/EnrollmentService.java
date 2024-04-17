package org.project.spring_mini_project.features.enrollment;

import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressResponse;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentResponse;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse enrollStudent(EnrollmentRequest enrollmentRequest);
    List<EnrollmentResponse> getEnrollments(int page,int size,String code, String courseTitle, String courseCategory, String studentUsername, Boolean is_certified);
    EnrollmentResponse getEnrollmentByCode(String code);
    EnrollmentProgressResponse updateEnrollmentProgress(String code, EnrollmentProgressRequest enrollmentProgressRequest);
    EnrollmentProgressResponse getEnrollmentProgress(String code);
    EnrollmentResponse certifyEnrollment(String code);
    EnrollmentResponse discardEnrollment(String code);

}

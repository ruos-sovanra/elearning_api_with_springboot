package org.project.spring_mini_project.features.course;

import org.project.spring_mini_project.features.course.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CourseCreateRequest courseCreateRequest);
    List<CourseResponse> findAllCourses(int page, int size);
    CourseDetailsResponse findCourseDetails(String alias);
    CourseResponse updateCourse(String alias, CourseUpdateRequest courseUpdateRequest);
//    CourseResponse updateCourseThumbnail(String alias, MultipartFile thumbnail);
    CourseResponse updateCourseCategories(String alias, CourseCategoryRequest courseCategoryRequest);
    CourseResponse disableCourse(String alias);
}

package org.project.spring_mini_project.features.course;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Categories;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Instructor;
import org.project.spring_mini_project.features.category.CategoryRepository;
import org.project.spring_mini_project.features.course.dto.*;
import org.project.spring_mini_project.features.instructor.InstructorRepository;
import org.project.spring_mini_project.mapper.CourseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final InstructorRepository instructorRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public CourseResponse createCourse(CourseCreateRequest courseCreateRequest) {
        // Fetch the Instructor and Category objects from the database
        Instructor instructor = instructorRepository.findById(courseCreateRequest.instructor_id().intValue())
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        Categories category = categoryRepository.findById(courseCreateRequest.Category_id())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Set the Instructor and Category objects in the Course object
        Course course = courseMapper.toCourse(courseCreateRequest);
        course.setInstructor(instructor);
        course.setCategories(category);

        course = courseRepository.save(course);
        System.out.println(course);
        return courseMapper.toCourseResponse(course);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CourseResponse> findAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Course> coursePage = courseRepository.findAll(pageable);
        return coursePage.stream()
                .map(courseMapper::toCourseResponse)
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    @Override
    public CourseDetailsResponse findCourseDetails(String alias) {
        Course course = courseRepository.findCourseByAlias(alias);
        System.out.println(course);// This will fetch the Instructor
        return courseMapper.toCourseDetailsResponse(course);
    }

    @Transactional
    @Override
    public CourseResponse updateCourse(String alias, CourseUpdateRequest courseUpdateRequest) {
        Course course = courseRepository.findCourseByAlias(alias);
        // Update course fields here
        course = courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    @Transactional
    @Override
    public CourseResponse updateCourseCategories(String alias, CourseCategoryRequest courseCategoryRequest) {
        Course course = courseRepository.findCourseByAlias(alias);
        // Update course categories here
        course = courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    @Transactional
    @Override
    public CourseResponse disableCourse(String alias) {
//        Course course = courseRepository.findByAlias(alias);
//        // Disable course here
//        course = courseRepository.save(course);
//        return courseMapper.toCourseResponse(course);
        return null;
    }
}
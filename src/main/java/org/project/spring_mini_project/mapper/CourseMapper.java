package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.project.spring_mini_project.domain.Categories;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Instructor;
import org.project.spring_mini_project.features.category.dto.CategoryResponse;
import org.project.spring_mini_project.features.course.dto.CourseCreateRequest;
import org.project.spring_mini_project.features.course.dto.CourseDetailsResponse;
import org.project.spring_mini_project.features.course.dto.CourseResponse;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseResponse toCourseResponse(Course course);

    Course toCourse(CourseCreateRequest courseCreateRequest);

    @Mapping(source = "instructor.given_name", target = "instructorGivenName")
    @Mapping(source = "categories.name", target = "categoryName")
    CourseDetailsResponse toCourseDetailsResponse(Course course);



    @Named("toInstructorResponse")
    default InstructorResponse toInstructorResponse(Instructor instructor) {
        if (instructor == null) {
            System.out.println("Instructor is null");
            // Create a default InstructorResponse object
            return null;
        }
        System.out.println("Instructor is not null");
        return InstructorResponse.builder()
                .id(instructor.getId())
                .given_name(instructor.getGiven_name())
                .build();
    }
    @Named("toCategoryResponse")
    default CategoryResponse toCategoryResponse(Categories categories) {
        if (categories == null) {
            // Handle null categories here
            return null;
        }
        return CategoryResponse.builder()
                .id(categories.getId())
                .name(categories.getName())
                .build();
    }
}
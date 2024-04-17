package org.project.spring_mini_project.features.course;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.project.spring_mini_project.features.course.dto.*;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/courses")
public class CourseRestController{
    private final CourseService courseService;

    @PostMapping
    @Operation(summary = "Create a course")
    public BaseResponse<CourseResponse> createCourse(@Valid @RequestBody CourseCreateRequest request){
        CourseResponse courseResponse = courseService.createCourse(request);
        return BaseResponse.<CourseResponse>createSuccess()
                .setPayload(courseResponse);
    }

    @GetMapping
    @Operation(summary = "Get all courses")
    public BaseResponse<List<CourseResponse>> findAllCourses(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return BaseResponse.<List<CourseResponse>>ok()
                .setPayload(courseService.findAllCourses(page, size));
    }
    @PutMapping("/{alias}")
    @Operation(summary = "Update a course")
    public BaseResponse<CourseResponse> updateCourse(@PathVariable String alias, @RequestBody CourseUpdateRequest request){
        CourseResponse courseResponse = courseService.updateCourse(alias, request);
        return BaseResponse.<CourseResponse>updateSuccess()
                .setPayload(courseResponse);
    }
    @GetMapping("/{alias}")
    @Operation(summary = "Get course by alias")
    public BaseResponse<CourseDetailsResponse> findCourseDetails(@PathVariable String alias){
        CourseDetailsResponse courseDetailsResponse = courseService.findCourseDetails(alias);
        return BaseResponse.<CourseDetailsResponse>ok()
                .setPayload(courseDetailsResponse);
    }
    @PutMapping("/{alias}/categories")
    @Operation(summary = "Update course categories")
    public BaseResponse<CourseResponse> updateCourseCategories(@PathVariable String alias, @RequestBody CourseCategoryRequest request){
        CourseResponse courseResponse = courseService.updateCourseCategories(alias, request);
        return BaseResponse.<CourseResponse>updateSuccess()
                .setPayload(courseResponse);
    }
    @PutMapping("/{alias}/disable")
    @Operation(summary = "Disable a course")
    public BaseResponse<CourseResponse> disableCourse(@PathVariable String alias){
        CourseResponse courseResponse = courseService.disableCourse(alias);
        return BaseResponse.<CourseResponse>updateSuccess()
                .setPayload(courseResponse);
    }


}

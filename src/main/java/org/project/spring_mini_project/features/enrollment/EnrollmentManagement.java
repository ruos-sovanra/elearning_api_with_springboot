package org.project.spring_mini_project.features.enrollment;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressResponse;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/enrollments")
@RequiredArgsConstructor
public class EnrollmentManagement {
    private final EnrollmentService enrollmentService;

    @GetMapping
    @Operation(summary = "Get all enrollments")
    public BaseResponse<List<EnrollmentResponse>> getEnrollments(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,@RequestParam(required = false) String code, @RequestParam(required = false) String courseTitle, @RequestParam(required = false) String courseCategory, @RequestParam(required = false) String studentUsername, @RequestParam(required = false) Boolean is_certified) {
        return BaseResponse.<List<EnrollmentResponse>>ok()
                .setPayload(enrollmentService.getEnrollments(page,size, code, courseTitle, courseCategory, studentUsername, is_certified));
    }

    @PostMapping
    @Operation(summary = "Enroll a student")
    public BaseResponse<EnrollmentResponse> enrollStudent(@Valid @RequestBody EnrollmentRequest enrollmentRequest) {
        EnrollmentResponse enrollmentResponse = enrollmentService.enrollStudent(enrollmentRequest);
        return BaseResponse.<EnrollmentResponse>createSuccess()
                .setPayload(enrollmentResponse);
    }

    @GetMapping("/{code}")
    @Operation(summary = "Get enrollment by code")
    public BaseResponse<EnrollmentResponse> getEnrollmentByCode(@PathVariable String code) {
        return BaseResponse.<EnrollmentResponse>ok()
                .setPayload(enrollmentService.getEnrollmentByCode(code));
    }

    @GetMapping("/{code}/progress")
    @Operation(summary = "Get enrollment progress")
    public BaseResponse<EnrollmentProgressResponse> getEnrollmentProgress(@PathVariable String code) {
        return BaseResponse.<EnrollmentProgressResponse>ok()
                .setPayload(enrollmentService.getEnrollmentProgress(code));
    }

    @PutMapping("/{code}/progress")
    @Operation(summary = "Update enrollment progress")
    public BaseResponse<EnrollmentProgressResponse> updateEnrollmentProgress(@PathVariable String code, @RequestBody EnrollmentProgressRequest enrollmentProgressRequest) {
        return BaseResponse.<EnrollmentProgressResponse>ok()
                .setPayload(enrollmentService.updateEnrollmentProgress(code, enrollmentProgressRequest));
    }

    @PatchMapping("/{code}/certify")
    @Operation(summary = "Certify enrollment")
    public BaseResponse<EnrollmentResponse> certifyEnrollment(@PathVariable String code) {
        return BaseResponse.<EnrollmentResponse>ok()
                .setPayload(enrollmentService.certifyEnrollment(code));
    }

    @PatchMapping("/{code}/discard")
    @Operation(summary = "Discard enrollment")
    public BaseResponse<EnrollmentResponse> discardEnrollment(@PathVariable String code) {
        return BaseResponse.<EnrollmentResponse>ok()
                .setPayload(enrollmentService.discardEnrollment(code));
    }

}

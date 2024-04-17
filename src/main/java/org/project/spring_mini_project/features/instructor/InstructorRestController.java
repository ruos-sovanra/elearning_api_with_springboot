package org.project.spring_mini_project.features.instructor;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.instructor.dto.InstructorCreateRequest;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/instructors")
@RequiredArgsConstructor
public class InstructorRestController {
    private final InstructorService instructorService;

    @PostMapping
    @Operation(summary = "Create an instructor")
    public BaseResponse<InstructorResponse> createInstructor(@RequestBody InstructorCreateRequest request){
        InstructorResponse instructorResponse = instructorService.createInstructor(request);
        return BaseResponse.<InstructorResponse>createSuccess()
                .setPayload(instructorResponse);
    }

    @GetMapping
    @Operation(summary = "Get all instructors")
    public BaseResponse<List<InstructorResponse>> findAllInstructors(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return BaseResponse.<List<InstructorResponse>>ok()
                .setPayload(instructorService.findAllInstructors(page, size));
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get instructor by username")
    public BaseResponse<InstructorResponse> findInstructorByUsername(@PathVariable String username){
        InstructorResponse instructorResponse = instructorService.findInstructorByUsername(username);
        return BaseResponse.<InstructorResponse>ok()
                .setPayload(instructorResponse);
    }

    @PutMapping("/{username}")
    @Operation(summary = "Update instructor by username")
    public BaseResponse<InstructorResponse> updateInstructor(@PathVariable String username, @RequestBody InstructorCreateRequest request){
        InstructorResponse instructorResponse = instructorService.updateInstructor(username, request);
        return BaseResponse.<InstructorResponse>updateSuccess()
                .setPayload(instructorResponse);
    }
}

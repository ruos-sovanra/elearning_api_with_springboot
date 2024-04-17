package org.project.spring_mini_project.features.student;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.student.dto.StudentCreateRequest;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.project.spring_mini_project.features.student.dto.StudentUpdateRequest;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentService studentService;

    @GetMapping
    @Operation(summary = "Get all students !")
    public BaseResponse<List<StudentRespone>> getAllStudents( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10")  int size){
        return BaseResponse.<List<StudentRespone>>ok()
                .setPayload(studentService.getAllStudents(page,size));
    }

    @PostMapping
    @Operation(summary = "Create Student !")
    public BaseResponse<StudentRespone> createStudent(@Valid @RequestBody StudentCreateRequest studentCreateRequest){
        return BaseResponse.<StudentRespone>createSuccess()
                .setPayload(studentService.createStudent(studentCreateRequest));
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get student by username !")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public BaseResponse<StudentRespone> getStudentByUsername(

            @Parameter(description = "Student username", required = true, example = "john_doe")

            @PathVariable  String username){
        return BaseResponse.<StudentRespone>notFound()
                .setPayload(studentService.getStudentByUsername(username));
    }

    @PutMapping("/{username}")
    @Operation(summary = "update student by username !")
    public BaseResponse<StudentRespone> updateStudentByUsername(@PathVariable String username, @RequestBody StudentUpdateRequest studentUpdateRequest){
        return BaseResponse.<StudentRespone>updateSuccess()
                .setPayload(studentService.updateStudentByUsername(username, studentUpdateRequest));
    }
}

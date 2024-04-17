package org.project.spring_mini_project.features.instructor;

import org.project.spring_mini_project.features.instructor.dto.InstructorCreateRequest;
import org.project.spring_mini_project.features.instructor.dto.InstructorResponse;
import org.project.spring_mini_project.features.instructor.dto.InstructorUpdateRequest;

import java.util.List;

public interface InstructorService {
    List<InstructorResponse> findAllInstructors(int page, int size);
    InstructorResponse createInstructor(InstructorCreateRequest instructorCreateRequest);
    InstructorResponse updateInstructor(String username, InstructorCreateRequest instructorCreateRequest);
    InstructorResponse findInstructorByUsername(String username);
}

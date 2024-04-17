package org.project.spring_mini_project.features.student;

import org.project.spring_mini_project.features.student.dto.StudentCreateRequest;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.project.spring_mini_project.features.student.dto.StudentUpdateRequest;

import java.util.List;

public interface StudentService {
    List<StudentRespone> getAllStudents(int page, int size);

    StudentRespone createStudent(StudentCreateRequest studentCreateRequest);

    StudentRespone getStudentByUsername(String username);

    StudentRespone updateStudentByUsername(String username, StudentUpdateRequest studentUpdateRequest);
}

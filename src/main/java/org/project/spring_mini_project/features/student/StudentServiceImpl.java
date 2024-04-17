package org.project.spring_mini_project.features.student;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.features.student.dto.StudentCreateRequest;
import org.project.spring_mini_project.features.student.dto.StudentRespone;
import org.project.spring_mini_project.features.student.dto.StudentUpdateRequest;
import org.project.spring_mini_project.features.user.UserRepository;
import org.project.spring_mini_project.mapper.StudentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    private final UserRepository userRepository;

    private final StudentMapper studentMapper;


    @Override
    public List<StudentRespone> getAllStudents(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Student> studentPage = studentRepository.findAll(pageable);
       return studentPage.stream().
               map(studentMapper::mapStudentToStudentResponse).toList();
    }

    @Override
    public StudentRespone createStudent(StudentCreateRequest studentCreateRequest) {
        var owner = userRepository.findById(studentCreateRequest.user_id())
                .orElseThrow(
                        () -> new NoSuchElementException(
                                "User ID = " + studentCreateRequest.user_id() + " is not a valid user"
                        )
                );

        var student=studentMapper.mapStudentRequestToStudent(studentCreateRequest);
        student.setUser(owner);

        var saveStudent=studentRepository.save(student);

        return studentMapper.mapStudentToStudentResponse(saveStudent);


    }

    @Override
    public StudentRespone getStudentByUsername(String username) {

        var student=studentRepository.findByUserUsername(username)
                .orElseThrow(
                        ()->new NoSuchElementException(
                                "Student with username = "+username+" not found"
                        )
                );
        return studentMapper.mapStudentToStudentResponse(student);
    }

    @Override
    public StudentRespone updateStudentByUsername(String username, StudentUpdateRequest studentUpdateRequest) {

        var findStudent=studentRepository.findByUserUsername(username)
                .orElseThrow(
                        ()->new NoSuchElementException(
                                "Student with username = "+username+" not found"
                        )
                );
        var updateStudent=studentMapper.mapStudentRequestToStudent(studentUpdateRequest);
        updateStudent.setId(findStudent.getId());

        return studentMapper.mapStudentToStudentResponse(studentRepository.save(updateStudent));
    }
}

package org.project.spring_mini_project.features.enrollment;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Course;
import org.project.spring_mini_project.domain.Enrollment;
import org.project.spring_mini_project.domain.Student;
import org.project.spring_mini_project.features.course.CourseRepository;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressResponse;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentRequest;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentResponse;
import org.project.spring_mini_project.features.enrollment.dto.EnrollmentProgressRequest;
import org.project.spring_mini_project.features.student.StudentRepository;
import org.project.spring_mini_project.mapper.EnrollmentMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentMapper enrollmentMapper;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;


    @Override
    public EnrollmentResponse enrollStudent(EnrollmentRequest enrollmentRequest) {
        Course course = courseRepository.findById(enrollmentRequest.course_id())
                .orElseThrow(() -> new NoSuchElementException( "Course not found"));
        Student student = studentRepository.findStudentById(enrollmentRequest.student_id().intValue());
        if (student == null) {
            throw new NoSuchElementException("Student not found");
        }
        Enrollment enrollment = enrollmentMapper.toEnrollment(enrollmentRequest);
        enrollment.setStudent(student);

        // Set the Course object to the Enrollment object
        enrollment.setCourse(course);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public List<EnrollmentResponse> getEnrollments(int page, int size, String code, String courseTitle, String courseCategory, String studentUsername, Boolean is_certified) {
        String isCertifiedString = (is_certified == null) ? "true" : is_certified.toString();
        Specification<Enrollment> spec = Specification
                .where(new EnrollmentSpecification("code", code))
                .and(new EnrollmentSpecification("courseTitle", courseTitle))
                .and(new EnrollmentSpecification("courseCategoriesName", courseCategory))
                .and(new EnrollmentSpecification("studentUserUsername", studentUsername))
                .and(new EnrollmentSpecification("isCertified", isCertifiedString)
                );
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "enrolledAt"));
        Page<Enrollment> enrollmentsPage = enrollmentRepository.findAll(spec, pageable);
        List<Enrollment> enrollments = enrollmentsPage.getContent();
        return enrollments.stream()
                .map(enrollmentMapper::toEnrollmentResponse)
                .collect(Collectors.toList());
    }


    @Override
    public EnrollmentResponse getEnrollmentByCode(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentProgressResponse updateEnrollmentProgress(String code, EnrollmentProgressRequest enrollmentProgressRequest) {
       Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        enrollment.setProgress(enrollmentProgressRequest.progress());
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentProgressResponse(enrollment);
    }

    @Override
    public EnrollmentProgressResponse getEnrollmentProgress(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        return enrollmentMapper.toEnrollmentProgressResponse(enrollment);
    }

    @Override
    public EnrollmentResponse certifyEnrollment(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        if (enrollment == null) {
            throw new NoSuchElementException("Enrollment not found");
        }
        enrollment.setIs_certified(true);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }

    @Override
    public EnrollmentResponse discardEnrollment(String code) {
        Enrollment enrollment = enrollmentRepository.findByCode(code);
        enrollment.setIs_deleted(true);
        enrollment = enrollmentRepository.save(enrollment);
        return enrollmentMapper.toEnrollmentResponse(enrollment);
    }
}

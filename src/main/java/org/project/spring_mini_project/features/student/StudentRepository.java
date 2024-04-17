package org.project.spring_mini_project.features.student;

import org.project.spring_mini_project.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface StudentRepository extends JpaRepository<Student, Long>
{
    Optional<Student> findByUserUsername(String username);
    Student findStudentById(Integer id);
}

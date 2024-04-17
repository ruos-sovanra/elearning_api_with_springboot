package org.project.spring_mini_project.features.instructor;

import org.project.spring_mini_project.domain.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{
    Optional<Instructor> findInstructorByUser_Username(String username);
}

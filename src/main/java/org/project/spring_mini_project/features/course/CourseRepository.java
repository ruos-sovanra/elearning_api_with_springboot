package org.project.spring_mini_project.features.course;

import org.project.spring_mini_project.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByAlias(String alias);
    Course findCourseById(Long id);
}

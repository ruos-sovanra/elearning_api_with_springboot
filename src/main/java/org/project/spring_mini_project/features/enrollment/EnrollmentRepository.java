package org.project.spring_mini_project.features.enrollment;

import org.project.spring_mini_project.domain.Enrollment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long>, JpaSpecificationExecutor<Enrollment> {
    Enrollment findByCode(String code);

    List<Enrollment> findAll(Specification<Enrollment> spec, Sort enrolledAt);
}

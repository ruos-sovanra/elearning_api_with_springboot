package org.project.spring_mini_project.features.enrollment.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import org.project.spring_mini_project.domain.Student;

import java.time.LocalDateTime;

@Builder
public record EnrollmentProgressRequest(
        Integer progress
) {
}

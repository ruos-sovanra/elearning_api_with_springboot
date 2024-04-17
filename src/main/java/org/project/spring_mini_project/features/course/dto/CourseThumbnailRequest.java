package org.project.spring_mini_project.features.course.dto;

import lombok.Builder;

@Builder
public record CourseThumbnailRequest(Long courseId, byte[] thumbnail) {
}
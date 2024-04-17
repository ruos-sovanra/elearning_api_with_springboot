package org.project.spring_mini_project.features.file.dto;

import lombok.Builder;

@Builder
public record FileResponse(String filename,
                           String fullUrl ,
                           String downloadUrl,
                           String fileType,
                           float size) {
}

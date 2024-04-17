package org.project.spring_mini_project.features.country.dto;

import lombok.Builder;

@Builder
public record CountryResponse(Long id,String name, String iso, String flag) {}

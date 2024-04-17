package org.project.spring_mini_project.features.country.dto;

import lombok.Builder;

@Builder
public record CountryDetailResponse(Long id,String name, String iso, String flag, String nice_name, Integer num_code, Integer phone_code) {}

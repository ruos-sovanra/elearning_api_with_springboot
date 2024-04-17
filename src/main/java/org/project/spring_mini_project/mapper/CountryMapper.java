package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.project.spring_mini_project.domain.Country;
import org.project.spring_mini_project.features.country.dto.CountryDetailResponse;
import org.project.spring_mini_project.features.country.dto.CountryResponse;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryResponse mapToCountryResponse(Country country);
    CountryDetailResponse mapToCountryDetailResponse(Country country);
}

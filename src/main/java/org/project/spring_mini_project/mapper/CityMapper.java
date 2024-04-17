package org.project.spring_mini_project.mapper;

import org.mapstruct.Mapper;
import org.project.spring_mini_project.domain.City;
import org.project.spring_mini_project.features.country.dto.CityResponse;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityResponse toCityResponse(City city);
}

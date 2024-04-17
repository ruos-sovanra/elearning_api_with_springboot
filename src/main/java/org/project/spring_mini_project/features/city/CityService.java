package org.project.spring_mini_project.features.city;

import org.project.spring_mini_project.features.country.dto.CityResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CityService {
    List<CityResponse> getAllCities(String name, Sort.Direction sort);
}

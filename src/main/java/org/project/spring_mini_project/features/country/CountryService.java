package org.project.spring_mini_project.features.country;

import org.project.spring_mini_project.features.country.dto.CityResponse;
import org.project.spring_mini_project.features.country.dto.CountryDetailResponse;
import org.project.spring_mini_project.features.country.dto.CountryResponse;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface CountryService {
    List<CountryDetailResponse> getAllCountries(String name, Sort.Direction sort);
    List<CityResponse> getCitiesByCountryIso(String iso);

}

package org.project.spring_mini_project.features.city;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.City;
import org.project.spring_mini_project.features.country.dto.CityResponse;
import org.project.spring_mini_project.mapper.CityMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService{
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;


    @Override
    public List<CityResponse> getAllCities(String name, Sort.Direction sort) {
        List<City> cities = cityRepository.findAll(Sort.by(sort, "name"));

        return cities.stream()
                .filter(city -> name == null || city.getName().toLowerCase().contains(name.toLowerCase()))
                .map(cityMapper::toCityResponse)
                .collect(Collectors.toList());
    }
}

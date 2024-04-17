package org.project.spring_mini_project.features.country;

import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.City;
import org.project.spring_mini_project.domain.Country;
import org.project.spring_mini_project.features.city.CityRepository;
import org.project.spring_mini_project.features.country.dto.CityResponse;
import org.project.spring_mini_project.features.country.dto.CountryDetailResponse;
import org.project.spring_mini_project.features.country.dto.CountryResponse;
import org.project.spring_mini_project.mapper.CityMapper;
import org.project.spring_mini_project.mapper.CountryMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;


    @Override
    public List<CountryDetailResponse> getAllCountries(String name, Sort.Direction sort) {
        List<Country> countries = countryRepository.findAll(Sort.by(sort, "name"));

        return countries.stream()
                .filter(country -> name == null || country.getName().toLowerCase().contains(name.toLowerCase()))
                .map(countryMapper::mapToCountryDetailResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<CityResponse> getCitiesByCountryIso(String iso) {
        List<City> cities = cityRepository.findCityByCountry_Iso(iso);
        if (cities == null) {
            throw new NoSuchElementException("Country not found");
        }
        System.out.println(cities);
        return cities.stream()
                .map(cityMapper::toCityResponse)
                .collect(Collectors.toList());
    }
}

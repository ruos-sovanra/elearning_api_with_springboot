package org.project.spring_mini_project.features.city;

import org.project.spring_mini_project.domain.City;
import org.project.spring_mini_project.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    List<City> findCityByCountry_Iso(String iso);
}

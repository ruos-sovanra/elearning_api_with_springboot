package org.project.spring_mini_project.features.country;

import org.project.spring_mini_project.domain.City;
import org.project.spring_mini_project.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Long> {

}

package org.project.spring_mini_project.features.country;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.country.dto.CityResponse;
import org.project.spring_mini_project.features.country.dto.CountryDetailResponse;
import org.project.spring_mini_project.features.country.dto.CountryResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
@RequiredArgsConstructor
public class CountyRestController {

    private final CountryService countryService;

    @GetMapping
    @Operation(summary = "Get all countries")
    public BaseResponse<List<CountryDetailResponse>> getAllCountries(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        return BaseResponse.<List<CountryDetailResponse>>ok()
                .setPayload(countryService.getAllCountries(name, sort));
    }

    @GetMapping("/{iso}/cities")
    @Operation(summary = "Get all cities by country iso")
    public BaseResponse<List<CityResponse>> getCitiesByCountryIso(String iso) {
        return BaseResponse.<List<CityResponse>>ok()
                .setPayload(countryService.getCitiesByCountryIso(iso));
    }

}

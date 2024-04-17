package org.project.spring_mini_project.features.city;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.features.country.dto.CityResponse;
import org.project.spring_mini_project.utils.BaseResponse;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/cities")
@RequiredArgsConstructor
public class CityRestController {
    private final CityService cityService;

    @GetMapping
    @Operation(summary = "Get all cities")
    public BaseResponse<List<CityResponse>> getAllCities(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "ASC") Sort.Direction sort) {
        return BaseResponse.<List<CityResponse>>ok()
                .setPayload(cityService.getAllCities(name, sort));
    }
}

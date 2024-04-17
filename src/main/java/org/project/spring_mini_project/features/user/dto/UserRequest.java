package org.project.spring_mini_project.features.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public record UserRequest(
        String address1,
        String address2,
        @NotEmpty
        String family_name,
        @NotEmpty
        String gender,
        @NotEmpty
        String given_name,
        @NotEmpty
        String national_id_card,
        @Size(max = 20, min = 8, message = "Password must be at least 8 characters long!")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one number!")
        String password,
        @NotEmpty
        String username,
        @Email(message = "Email format is not correct!")
        String email,
        String phone_number,
        String profile,
        Integer city_id,
        Long country_id,
//        @NotEmpty
//        @DateTimeFormat(pattern = "yyyy-MM-dd")
//        LocalDate dob,
        @NotEmpty
        Set<String> roleNames) {
}

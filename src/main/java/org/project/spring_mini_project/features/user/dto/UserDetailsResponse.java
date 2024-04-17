package org.project.spring_mini_project.features.user.dto;

import lombok.Builder;
import org.project.spring_mini_project.features.role.dto.RoleResponse;

import java.util.Set;
import java.util.UUID;

@Builder
public record UserDetailsResponse(Long id,
                                  String username,
                                  String email,
                                  String family_name,
                                  String given_name,
                                  String gender,
                                    String phone_number,
                                    String address1,
                                    String address2,
                                    String city_name,
                                    String country_name,
                                    String national_id_card,
                                    Boolean is_deleted,
                                    Boolean is_verified,
                                    String profile,
                                    String verification_code,
                                  UUID uuid,
                                  Set<RoleResponse> roles) {
}

package org.project.spring_mini_project.features.user;

import org.project.spring_mini_project.features.user.dto.UserDetailsResponse;
import org.project.spring_mini_project.features.user.dto.UserRequest;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDetailsResponse> findAllUsers(String username, String email, String nationalIdCard, String phone_number, String name, String gender, Set<String> roleNames);
    UserDetailsResponse createUser(UserRequest userRequest);
    UserDetailsResponse findUserByUsername(String username);
    UserDetailsResponse disableUser(String username);
    UserDetailsResponse enableUser(String username);
    void deleteUser(String username);
}

package org.project.spring_mini_project.features.user;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.project.spring_mini_project.domain.Role;
import org.project.spring_mini_project.domain.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<User> {
    private final String property;
    private final String value;

    public UserSpecification(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.value == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }

        if (this.property.equals("roles")) {
            // Join the User with the Role entity and filter by role name
            Join<User, Role> rolesJoin = root.join("roles");
            return criteriaBuilder.equal(
                    criteriaBuilder.lower(rolesJoin.get("name")), // convert to lowercase
                    this.value.toLowerCase()
            );
        } else {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(this.property)), // convert to lowercase
                    "%" + this.value.toLowerCase() + "%" // wrap value in '%' for LIKE query
            );
        }
    }
}

package org.project.spring_mini_project.features.enrollment;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Join;
import org.project.spring_mini_project.domain.*;
import org.springframework.data.jpa.domain.Specification;

public class EnrollmentSpecification implements Specification<Enrollment> {
    private final String property;
    private final String value;

    public EnrollmentSpecification(String property, String value) {
        this.property = property;
        this.value = value;
    }

    @Override
    public Predicate toPredicate(Root<Enrollment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if (this.value == null) {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
//        if (this.property.equals("roles")) {
//            // Join the User with the Role entity and filter by role name
//            Join<User, Role> rolesJoin = root.join("roles");
//            return criteriaBuilder.equal(
//                    criteriaBuilder.lower(rolesJoin.get("name")), // convert to lowercase
//                    this.value.toLowerCase()
//            );
//        } else {
//            return criteriaBuilder.like(
//                    criteriaBuilder.lower(root.get(this.property)), // convert to lowercase
//                    "%" + this.value.toLowerCase() + "%" // wrap value in '%' for LIKE query
//            );
//        }

        if (this.property.equals("code")) {
            return criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("code")), // convert to lowercase
                    "%" + this.value.toLowerCase() + "%" // wrap value in '%' for LIKE query
            );
        } else if (this.property.equals("courseTitle")) {
            Join<Enrollment, Course> courseJoin = root.join("course");
            return criteriaBuilder.like(
                    criteriaBuilder.lower(courseJoin.get("title")), // convert to lowercase
                    "%" + this.value.toLowerCase() + "%" // wrap value in '%' for LIKE query
            );
        } else if (this.property.equals("courseCategoriesName")) {
            Join<Enrollment, Course> courseJoin = root.join("course").join("categories");
            return criteriaBuilder.like(
                    criteriaBuilder.lower(courseJoin.get("name")), // convert to lowercase
                    "%" + this.value.toLowerCase() + "%" // wrap value in '%' for LIKE query
            );
        } else if (this.property.equals("studentUserUsername")) {
            Join<Enrollment, Student> studentJoin = root.join("student").join("user");
            return criteriaBuilder.like(
                    criteriaBuilder.lower(studentJoin.get("username")), // convert to lowercase
                    "%" + this.value.toLowerCase() + "%" // wrap value in '%' for LIKE query
            );
        } else if (this.property.equals("isCertified")) {
            return criteriaBuilder.equal(root.get("is_certified"), Boolean.valueOf(this.value));
        } else {
            return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // always true = no filtering
        }
    }
}
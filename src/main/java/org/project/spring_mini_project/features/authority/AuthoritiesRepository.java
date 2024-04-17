package org.project.spring_mini_project.features.authority;

import org.project.spring_mini_project.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, Long> {
}

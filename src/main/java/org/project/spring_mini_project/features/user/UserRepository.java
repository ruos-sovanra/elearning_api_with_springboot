package org.project.spring_mini_project.features.user;

import org.project.spring_mini_project.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.is_deleted = true WHERE u.username = :username")
    void disableUser(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("UPDATE User u SET u.is_deleted = false WHERE u.username = :username")
    void enableUser(@Param("username") String username);

    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.username = :username")
    void deleteByUsername(@Param("username") String username);

    List<User> findAll(Specification<User> spec, Sort sort);
}

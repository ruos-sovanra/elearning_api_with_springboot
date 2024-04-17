package org.project.spring_mini_project.features.category;

import org.project.spring_mini_project.domain.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Long> {
    Categories findCategoriesByAlias(String alias);


    @Modifying
    @Query("UPDATE Categories c SET c.isDeleted = :isDeleted WHERE c.alias = :alias")
    int updateIsDeletedStatusByAlias(@Param("alias") String alias, @Param("isDeleted") boolean isDeleted);
}

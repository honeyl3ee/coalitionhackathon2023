package com.ft.modumoa.repository;

import com.ft.modumoa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "SELECT * FROM category WHERE type = :type", nativeQuery = true)
    Category getByType(@Param("type") String type);
}

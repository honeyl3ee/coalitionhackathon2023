package com.ft.modumoa.repository;

import com.ft.modumoa.entity.Category;
import com.ft.modumoa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findByType(String type);
}

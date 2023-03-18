package com.ft.modumoa.repository;

import com.ft.modumoa.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    @Test
    void 카테고리_초기화() {

        categoryRepository.save(new Category(null, "배달"));
        categoryRepository.save(new Category(null, "게임"));
        categoryRepository.save(new Category(null, "동아리"));
        categoryRepository.save(new Category(null, "컨퍼런스"));
        categoryRepository.save(new Category(null, "과제"));
        categoryRepository.save(new Category(null, "봉사"));
        categoryRepository.save(new Category(null, "탁구"));
        categoryRepository.save(new Category(null, "기타"));
    }

    @Test
    void 카테고리_검색(){

        Category category = categoryRepository.getByType("게임");
        System.out.println(category.getId());
        System.out.println(category.getType());
    }
}
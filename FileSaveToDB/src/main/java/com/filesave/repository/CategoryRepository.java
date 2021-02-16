package com.filesave.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.filesave.entity.CategoryEntity;


public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

	CategoryEntity findByCategoryName(String categoryName);
	}

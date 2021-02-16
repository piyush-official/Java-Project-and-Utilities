package com.filesave.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.filesave.entity.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query("Select pr from ProductEntity pr where pr.status=1 "
			+ "AND (COALESCE(:productName, null) is null or pr.productName = :productName) "
			+ "AND (COALESCE(:productColor, null) is null or lower(pr.productColor) = lower(:productColor)) "
			+ "AND (COALESCE(:categoryId, null) is null or pr.category.id = :categoryId) order by id desc")
	List<ProductEntity> findProductByFilters(@Param("productName") String productName,
			@Param("productColor") String productColor, @Param("categoryId") Long categoryId);

}

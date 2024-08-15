package com.alrussy.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByName(String name);
	@Transactional
	@Modifying
	@Query(value = "delete from brand_category c where c.category_id = ?1  ",nativeQuery = true)
	int deleteBrandCategory(Long categoryId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from category_details_name cd where cd.category_id = ?1",nativeQuery = true)
	int deleteCategorydetailsName(Long categoryId);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO category_details_name (category_id, details_name_id) VALUES (?1, ?2)",nativeQuery = true)
	int saveWithDetailsName(Long categoryId,Long detailsnameId);

	@Transactional
	@Modifying
	@Query(value = "INSERT INTO brand_category (category_id, brand_id) VALUES (?1, ?2)",nativeQuery = true)
	int saveWithBrands(Long categoryId,Long brandId);
	
	
	@EntityGraph(attributePaths = {"brandCategory.brand"})
	List<Category> findAll();
}


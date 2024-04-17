package com.alrussy.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {

	List<Brand> findByName(String name);

	@Query(value = "SELECT * FROM brand_category bc join brands b on (bc.brand_id = b.id) where bc.category_id = ?1",nativeQuery = true)
	List<Brand> findByCategory(Long id);
	
	@Transactional
	@Modifying
	@Query(value = "INSERT INTO brand_category (category_id, brand_id) VALUES (?1, ?2)",nativeQuery = true)
	int saveWithCategories(Long categoryId,Long brandId);
	
	@Transactional
	@Modifying
	@Query(value = "delete from brand_category b where b.brand_id = ?1",nativeQuery = true)
	int deleteBrand(Long brandId);
	@Transactional
	@Modifying
	@Query(value = "delete from products p where p.brand_category_category_id = ?1",nativeQuery = true)
	int deleteProduct(Long categoryId);
	

}

package com.alrussy.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.productservice.entity.SkuProduct;

public interface SkuProductRepository extends JpaRepository<SkuProduct, String> {

	List<SkuProduct> findByProductIdProductId(Long id);

	
	

	

}

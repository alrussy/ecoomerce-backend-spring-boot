package com.alrussy.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.ProductId;

public interface ProductRepository extends JpaRepository<Product, ProductId>,JpaSpecificationExecutor<Product> {

	
	Optional<Product> findByIdProductId(long id);
	Boolean existsByName(String name);
	
	@Transactional
	@Modifying
	int deleteByIdProductId(long id);
}

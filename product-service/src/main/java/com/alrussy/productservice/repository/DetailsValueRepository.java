package com.alrussy.productservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.DetailsValue;

public interface DetailsValueRepository extends JpaRepository<DetailsValue, Long> {

	

}

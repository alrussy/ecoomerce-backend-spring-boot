package com.alrussy.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;
import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.DetailsValue;

public interface DetailsValueRepository extends JpaRepository<DetailsValue, Long> {

	List<DetailsValueResponse> findByDetailsNameId(Long id);

	

}

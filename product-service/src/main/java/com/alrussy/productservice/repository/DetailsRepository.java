package com.alrussy.productservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.productservice.dto.details_dto.DetailsResponse;
import com.alrussy.productservice.entity.Details;
import com.alrussy.productservice.entity.id.DetailsId;

public interface DetailsRepository extends JpaRepository<Details, DetailsId> {

	List<DetailsResponse> findByDetailsNameId(Long id);
	Optional<Details> findByValue(String value);
	Optional<Details> findByIdDetailsId(Long id);
	

	

}

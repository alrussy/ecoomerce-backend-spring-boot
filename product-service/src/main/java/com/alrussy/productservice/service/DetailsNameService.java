package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.dto.details_name_dto.DetailsNameRequest;
import com.alrussy.productservice.dto.details_name_dto.DetailsNameResponse;
import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.repository.DetailsNameRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailsNameService {

	private final DetailsNameRepository detailsNameRepository;

	public List<DetailsNameResponse> findAll() {

		return detailsNameRepository.findAll().stream().map(detailsName -> detailsName.mapToDetailsNameResponseOutValues()).toList();
	}

	public DetailsNameResponse findById(Long id) {

		return detailsNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("DetailsName  By ID = "+id+" Is Not Found")).mapToDetailsNameResponseOutValues();
	}
	
	@Transactional
	public DetailsNameResponse save(DetailsNameRequest detailsName) {
		return detailsNameRepository.save(detailsName.mapToDetailsName()).mapToDetailsNameResponseOutValues();
	}
		
		
	
	@Transactional
	public void delete(Long id) {
		 detailsNameRepository.deleteAllByIdInBatch(List.of(id));
	}


	@Transactional
	public DetailsNameResponse update(Long id,DetailsNameRequest detailsName) {
		
		DetailsName detailsNameFind= detailsNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("DetailsName  By ID = "+id+" Is Not Found "));
		if(detailsName.getName()!=null) {
		detailsNameFind.setDetailsName(detailsName.getName());
		}
		return 		detailsNameRepository.save(detailsNameFind).mapToDetailsNameResponseOutValues();
	}
	
	

	
}

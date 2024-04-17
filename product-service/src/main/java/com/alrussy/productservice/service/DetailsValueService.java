package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.dto.details_value_dto.DetailsValueRequest;
import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;
import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.entity.DetailsValue;
import com.alrussy.productservice.repository.DetailsValueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailsValueService {

	private final DetailsValueRepository detailsValueRepository;

	public List<DetailsValueResponse> findAll() {

		return detailsValueRepository.findAll().stream().map(detailsValue -> detailsValue.mapToDetailsValueResponse()).toList();
	}

	public DetailsValueResponse findById(Long id) {

		return detailsValueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("DetailsValue  By ID = "+id+" Is Not Found")).mapToDetailsValueResponse();
	}
	
	@Transactional
	public DetailsValueResponse save(DetailsValueRequest detailsValue) {
		if (detailsValue != null && detailsValue.getDetailsNameId()!=null) {
			return detailsValueRepository.save(detailsValue.mapToDetailsValue()).mapToDetailsValueResponse();			
		}
		
		else
			throw new IllegalArgumentException("detailsNameId must not null or empty... plaese add detailsName");
		
		
		
		
		
	}
		
		
	
	@Transactional
	public void delete(Long id) {
		 detailsValueRepository.deleteById(id);
	}


	@Transactional
	public DetailsValueResponse update(Long id,DetailsValueRequest detailsValue) {
		
		DetailsValue detailsValueFind= detailsValueRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("DetailsValue  By ID = "+id+" Is Not Found "));
		if(detailsValue.getValue()!=null) {
		detailsValueFind.setDetailsValue(detailsValue.getValue());
		}

		if(detailsValue.getDetailsNameId()!=null) {
			
			detailsValueFind.setDetailsName(DetailsName.builder().id(detailsValue.getDetailsNameId()).build());
		}
		
		return 		detailsValueRepository.save(detailsValueFind).mapToDetailsValueResponse();
	}
	
	

	
}

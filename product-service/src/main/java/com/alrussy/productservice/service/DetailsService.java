package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.dto.details_dto.DetailsRequest;
import com.alrussy.productservice.dto.details_dto.DetailsResponse;
import com.alrussy.productservice.entity.Details;
import com.alrussy.productservice.repository.DetailsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetailsService {

	private final DetailsRepository detailsRepository;

	public List<DetailsResponse> findAll() {

		return detailsRepository.findAll().stream().map(detailsValue -> detailsValue.mapToDetailsValueResponse()).toList();
	}

	public DetailsResponse findById(Long id) {

		return detailsRepository.findByIdDetailsId(id).orElseThrow(() -> new IllegalArgumentException("DetailsValue  By ID = "+id+" Is Not Found")).mapToDetailsValueResponse();
	}
	
	@Transactional
	public DetailsResponse save(DetailsRequest detailsValue) {
		if (detailsValue != null && detailsValue.getDetailsNameId()!=null) {
			return detailsRepository.save(detailsValue.mapToDetailsValue()).mapToDetailsValueResponse();			
		}
		
		else
			throw new IllegalArgumentException("detailsNameId must not null or empty... plaese add detailsName");
		
		
		
		
		
	}
		
		
	
	@Transactional
	public void delete(Long id) {
		Details details=detailsRepository.findByIdDetailsId(id).orElseThrow();
		 detailsRepository.delete(details);
	}


	@Transactional
	public DetailsResponse update(Long id,DetailsRequest details) {
		
		Details detailsValueFind= detailsRepository.findByIdDetailsId(id).orElseThrow(() -> new IllegalArgumentException("DetailsValue  By ID = "+id+" Is Not Found "));
		if(details.getValue()!=null) {
		detailsValueFind.setValue(details.getValue());
		}

		if(details.getDetailsNameId()!=null) {
			
			detailsValueFind.setDetailsName(DetailsName.builder().id(details.getDetailsNameId()).build());
		}
		
		return 		detailsRepository.save(detailsValueFind).mapToDetailsValueResponse();
	}

	public List<DetailsResponse> findByDetailsName(Long id) {
		// TODO Auto-generated method stub
		return detailsRepository.findByDetailsNameId(id);
	}
	
	

	
}

package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.dto.brand_dto.BrandRequest;
import com.alrussy.productservice.dto.brand_dto.BrandResponse;
import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.repository.BrandRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {

	private final BrandRepository brandRepository;

	public List<BrandResponse> findAll() {

		return brandRepository.findAll().stream().map(brand -> brand.mapToBrandResponse()).toList();
	}

	public BrandResponse findById(Long id) {

		return brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand  By ID = "+id+" Is Not Found")).mapToBrandResponse();
	}
	
	@Transactional
	public BrandResponse save(BrandRequest brand) {
		if (brand != null && brand.getCategoryIds()!=null) {
			final Brand brandReturn = brandRepository.saveAndFlush(brand.mapToBrand());
			brand.getCategoryIds().stream().forEach(t -> brandRepository.saveWithCategories(t, brandReturn.getId()));
			
			return brandReturn.mapToBrandResponse();
			
		}
		
		else
			throw new IllegalArgumentException("CategoryIds[] must not empty... plaese add one category at least");
		
		
		
		
	}
	
	
	@Transactional
	public void delete(Long id) {
		 brandRepository.deleteBrand(id);
		 brandRepository.deleteAllByIdInBatch(List.of(id));
	}


	@Transactional
	public BrandResponse update(Long id,BrandRequest brand) {
		
		Brand brandFind= brandRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Brand  By ID = "+id+" Is Not Found "));
		if(brand.getName()!=null) {
		brandFind.setName(brand.getName());
		}
		if(brand.getImageUrl()!=null)
		brandFind.setImageUrl(brand.getImageUrl());
		
		if(brand.getCategoryIds()!=null) {
			brandRepository.deleteBrand(id);
			brand.getCategoryIds().stream().forEach(t -> brandRepository.saveWithCategories(t,id));
		}
		
		return 		brandRepository.save(brandFind).mapToBrandResponse();
	}
	
	

	public List<BrandResponse> findByCategory(Long id) {
		
		return brandRepository.findByCategory(id).stream().map(Brand::mapToBrandResponse).toList();
	}
	
	
	
public List<BrandResponse> findByName(String name) {
		
		return brandRepository.findByName(name).stream().map(Brand::mapToBrandResponse).toList();
	}
}

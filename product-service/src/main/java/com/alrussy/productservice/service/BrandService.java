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

		return brandRepository.findAll().stream().map(brand -> brand.mapToBrandResponseOutCategory()).toList();
	}

	public BrandResponse findById(Long id) {

		return brandRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Brand  By ID = " + id + " Is Not Found"))
				.mapToBrandResponseOutCategory();
	}

	public List<BrandResponse> findByCategory(Long id) {

		return brandRepository.findByCategory(id).stream().map(Brand::mapToBrandResponseOutCategory).toList();
	}

	public List<BrandResponse> findByName(String name) {

		return brandRepository.findByName(name).stream().map(Brand::mapToBrandResponseOutCategory).toList();
	}

	@Transactional
	public BrandResponse save(BrandRequest brand) {
		final Brand brandReturn = brandRepository.save(brand.mapToBrand());

		if (brand.getCategoryIds() != null && !brand.getCategoryIds().isEmpty())
			brand.getCategoryIds().stream().forEach(t -> brandRepository.saveWithCategories(t, brandReturn.getId()));

		return brandReturn.mapToBrandResponseOutCategory();

	}

	@Transactional
	public void delete(Long id) {
		brandRepository.deleteBrand(id);
		brandRepository.deleteAllByIdInBatch(List.of(id));
	}

	@Transactional
	public BrandResponse update(Long id, BrandRequest brand) {

		Brand brandFind = brandRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Brand  By ID = " + id + " Is Not Found "));
		if (brand.getName() != null) {
			brandFind.setName(brand.getName());
		}
		if (brand.getImageUrl() != null)
			brandFind.setImageUrl(brand.getImageUrl());

		if (brand.getCategoryIds() != null) {
			brand.getCategoryIds().stream().forEach(t -> brandRepository.saveWithCategories(t, id));
		}

		return brandRepository.save(brandFind).mapToBrandResponseOutCategory();
	}

	public Long count() {
		return brandRepository.count();
	}
}

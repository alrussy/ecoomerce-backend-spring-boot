package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.dto.category_dto.CategoryRequest;
import com.alrussy.productservice.dto.category_dto.CategoryResponse;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public List<CategoryResponse> findAll() {
		return categoryRepository.findAll().stream().map(Category::mapToCategoryResponseWithDetailsNameAndBrand).toList();
	} 

	public CategoryResponse findById(Long id) {
		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category  By ID = " + id + " Is Not Found"))
				.mapToCategoryResponseWithDetailsNameAndBrand();
	}

	public List<CategoryResponse> findByName(String name) {

		return categoryRepository.findByName(name).stream().map(Category::mapToCategoryResponseWithDetailsNameAndBrand).toList();
	}

	@Transactional
	public CategoryResponse save(CategoryRequest category) {

		Category categorySave = categoryRepository.save(category.mapToCategory());

		category.getBrandIds().stream()
		.forEach(t -> categoryRepository.saveWithBrands(categorySave.getId(),t));
		
		category.getDetailsNameIds().stream()
				.forEach(t -> categoryRepository.saveWithDetailsName(categorySave.getId(),t));
		return categorySave.mapToCategoryResponseOutDetailsNameAndBrand();
	}

	@Transactional
	public String delete(Long id) {
		categoryRepository.deleteBrandCategory(id);
		categoryRepository.deleteCategorydetailsName(id);
		categoryRepository.deleteAllByIdInBatch(List.of(id));
		return "delete By id :"+id+" is success";
	}

	@Transactional
	public CategoryResponse update(Long id, CategoryRequest categoryRequest) {

		Category categoryFind = categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("category  By ID = " + id + " Is Not Found "));
		if (categoryRequest.getName() != null) {
			categoryFind.setName(categoryRequest.getName());
		}
		if (categoryRequest.getImageUrl() != null)
			categoryFind.setImageUrl(categoryRequest.getImageUrl());
		if (categoryRequest.getDetailsNameIds() != null && !categoryRequest.getDetailsNameIds().isEmpty()) {
			categoryRepository.deleteCategorydetailsName(id);
			categoryRequest.getDetailsNameIds().stream().forEach(t -> categoryRepository.saveWithDetailsName(id, t));
		}

		if (categoryRequest.getBrandIds() != null && !categoryRequest.getBrandIds().isEmpty()) {
			categoryRepository.deleteBrandCategory(id);
			categoryRequest.getBrandIds().stream().forEach(t -> categoryRepository.saveWithBrands(id, t));
		}
		return categoryRepository.save(categoryFind).mapToCategoryResponseWithDetailsNameAndBrand();
	}

}

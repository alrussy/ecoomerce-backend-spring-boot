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

		return categoryRepository.findAll().stream().map(Category::mapToCategoryResponseOutDetailsName).toList();
	}

	public CategoryResponse findById(Long id) {

		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category  By ID = " + id + " Is Not Found"))
				.mapToCategoryResponseOutDetailsName();
	}

	public List<CategoryResponse> findByName(String name) {

		return categoryRepository.findByName(name).stream().map(Category::mapToCategoryResponseOutDetailsName).toList();
	}

	@Transactional
	public CategoryResponse save(CategoryRequest category) {
		Category categorySave= categoryRepository.save(category.mapToCategory());
		category.getDetailsNameIds().stream().forEach(t -> categoryRepository.saveWithDetailsName(t, categorySave.getId()));
		return categorySave.mapToCategoryResponseOutDetailsName();
	}

	public void delete(Long id) {
		categoryRepository.deleteAllByIdInBatch(List.of(id));
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
		if(categoryRequest.getDetailsNameIds()!=null && !categoryRequest.getDetailsNameIds().isEmpty()) {
			categoryRequest.getDetailsNameIds().stream().forEach(t -> categoryRepository.saveWithDetailsName(id,t));
	
		}

		return categoryRepository.save(categoryFind).mapToCategoryResponseOutDetailsName();
	}
}

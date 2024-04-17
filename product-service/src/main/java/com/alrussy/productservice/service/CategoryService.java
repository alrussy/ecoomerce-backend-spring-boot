package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.dto.category_dto.CategoryRequest;
import com.alrussy.productservice.dto.category_dto.CategoryResponse;
import com.alrussy.productservice.dto.category_dto.CategoryRequest;
import com.alrussy.productservice.dto.category_dto.CategoryResponse;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.repository.CategoryRepository;
import com.alrussy.productservice.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	public List<CategoryResponse> findAll() {

		return categoryRepository.findAll().stream().map(Category::mapToCategoryResponse).toList();
	}

	public CategoryResponse findById(Long id) {

		return categoryRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Category  By ID = " + id + " Is Not Found"))
				.mapToCategoryResponse();
	}

	public List<CategoryResponse> findByName(String name) {

		return categoryRepository.findByName(name).stream().map(Category::mapToCategoryResponse).toList();
	}

	public CategoryResponse save(CategoryRequest category) {
		return categoryRepository.save(category.mapToCategory()).mapToCategoryResponse();
	}

	@Transactional
	public void delete(Long id) {
		categoryRepository.deleteProduct(id);
		categoryRepository.deleteCategory(id);
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

		return categoryRepository.save(categoryFind).mapToCategoryResponse();
	}
}

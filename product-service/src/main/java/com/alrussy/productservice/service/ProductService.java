package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.dto.product_dto.ProductRequest;
import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.DeparmentId;
import com.alrussy.productservice.entity.table.BrandCategory;
import com.alrussy.productservice.repository.ProductRepository;
import com.alrussy.productservice.repository.specification.ProductSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<ProductResponse> findAll() {
		return productRepository.findAll().stream().map(Product::mapToproductResponse).toList();
	}

	public ProductResponse findById(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("the record is not found"))
				.mapToproductResponse();

	}

	public ProductResponse save(ProductRequest productRequest) {

		return productRepository.save(productRequest.mapToprProduct()).mapToproductResponse();
	}

	public void update(Long id, ProductRequest productRequest) {

		Product product = productRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("the record is not found"));
		if (productRequest.name() != null || !productRequest.name().isEmpty()) {
			product.setName(productRequest.name());
		}
		if (productRequest.price() != null) {
			product.setPrice(productRequest.price());
		}
		if (productRequest.discount() != null) {
			product.setDiscount(productRequest.discount());
			;
		}
		if (productRequest.currency() != null || !productRequest.currency().isEmpty()) {
			product.setCurrency(productRequest.currency());
		}
		if (productRequest.isFeature() != null) {
			product.setIsFeature(productRequest.isFeature());
		}
		if (productRequest.departmentId() != null) {
			product.setDepartment(Department.builder().id(DeparmentId.builder().departmentId(productRequest.departmentId()).categoryId(productRequest.categoryId()).build()).build());
		}
		if (productRequest.brandId() != null && productRequest.categoryId() != null) {
			product.setBrandCategory(
					BrandCategory.builder()
							.brandCategoryId(BrandCategoryId.builder()
									.brandId(productRequest.brandId()).categoryId(productRequest.categoryId()).build())
							.build());
		}
	}

	public String delete(Long id) {
		productRepository.deleteById(id);
		return "delete is Successfuly";
	}

	public List<ProductResponse> filter(ProductFilter filter) {
		ProductSpecification productSpecification = new ProductSpecification(filter);
		return productRepository.findAll(productSpecification).stream().map(producr -> producr.mapToproductResponse())
				.toList();

	}
}

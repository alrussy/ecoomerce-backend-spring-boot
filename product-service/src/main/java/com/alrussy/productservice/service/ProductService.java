package com.alrussy.productservice.service;

import java.util.List;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.detDSA;
import org.springframework.stereotype.Service;

import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.dto.product_dto.ProductRequest;
import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.DepartmentId;
import com.alrussy.productservice.entity.table.BrandCategory;
import com.alrussy.productservice.repository.ProductRepository;
import com.alrussy.productservice.repository.specification.ProductSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<ProductResponse> findAll() {
		return productRepository.findAll().stream().map(Product::mapToproductResponseWithCategoryBrandAndDepartment).toList();
	}
	public List<ProductResponse> filter(ProductFilter filter) {
		ProductSpecification productSpecification = new ProductSpecification(filter);
		return productRepository.findAll(productSpecification).stream().map(producr -> producr.mapToproductResponseWithCategoryBrandAndDepartment())
				.toList();
	}
	public ProductResponse findByProductId(Long id) {
		return productRepository.findByIdProductId(id).orElseThrow(() -> new IllegalArgumentException("the record is not found"))
				.mapToproductResponseWithCategoryBrandAndDepartment();

	}
	public ProductResponse save(ProductRequest productRequest) {
		if(productRepository.existsByName(productRequest.name())) {
			throw new IllegalArgumentException("the name of product \""+productRequest.name()+"\" is exist");
			
		}
		return productRepository.save(productRequest.mapToprProduct()).mapToproductResponseWithCategoryBrandAndDepartment();
	}
	
	public ProductResponse update(Long id, ProductRequest productRequest) {
		ProductResponse  response=null;
		Product product = productRepository.findByIdProductId(id)
				.orElseThrow(() -> new IllegalArgumentException("the record is not found"));

		
		if(productRequest.categoryId() == product.getId().getCategoryId()) {
			
		
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
			
		
			if (productRequest.departmentId() != null && productRequest.categoryId()!=null) {
				product.setDepartment(Department.builder().id(DepartmentId.builder().departmentId(productRequest.departmentId()).categoryId(productRequest.categoryId()).build()).build());
			}
			
			if (productRequest.brandId() != null && productRequest.categoryId() != null) {
				product.setBrandCategory(
						BrandCategory.builder()
								.brandCategoryId(BrandCategoryId.builder()
										.brandId(productRequest.brandId()).categoryId(productRequest.categoryId()).build())
								.build());
			}
			
			response= productRepository.save(product).mapToproductResponseWithCategoryBrandAndDepartment();
		}
			
		else {
			delete(id);
			response=save(productRequest);
		}
		
		return response;
	}

	public String delete(Long id) {
		productRepository.deleteByIdProductId(id);
		return "delete is Successfuly";
	}
	
}

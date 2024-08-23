package com.alrussy.productservice.service;

import java.util.List;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.detDSA;
import org.springframework.stereotype.Service;

import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.dto.product_dto.ProductRequest;
import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.DepartmentId;
import com.alrussy.productservice.entity.table.BrandCategory;
import com.alrussy.productservice.repository.ProductRepository;
import com.alrussy.productservice.repository.specification.ProductSpecification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
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

		return productRepository.save(productRequest.mapToprProduct()).mapToproductResponseOutCategoryBrandAndDepartment();
	}
	
	public ProductResponse update(Long id, ProductRequest productRequest) {
		ProductResponse  response=null;
		Product product = productRepository.findByIdProductId(id)
				.orElseThrow(() -> new IllegalArgumentException("the record is not found"));
			if (productRequest.name() != null && !productRequest.name().isEmpty()) {
				product.setName(productRequest.name());
			}
			if (productRequest.price() != null) {
				product.setPrice(productRequest.price());
			}
			if (productRequest.discount() != null) {
				product.setDiscount(productRequest.discount());
				;
			}
			
			if (productRequest.currency() != null && !productRequest.currency().isEmpty()) {
				product.setCurrency(productRequest.currency());
			}
			if (productRequest.isFeature() != null) {
				product.setIsFeature(productRequest.isFeature());
			}
			
		
			if (productRequest.departmentId() != null && productRequest.categoryId()!=null) {
				product.setDepartment(Department.builder().id(DepartmentId.builder().departmentId(productRequest.departmentId()).categoryId(productRequest.categoryId()).build()).build());
			}
			
			if (productRequest.brandId() != null && productRequest.categoryId() != null) {
				log.info(" Brand Id={}",productRequest.brandId());
				product.setBrand( Brand.builder().id(productRequest.brandId()).build());
			}
			
			response= productRepository.save(product).mapToproductResponseOutCategoryBrandAndDepartment();
		return response;
	}

	public String delete(Long id) {
		Product product = productRepository.findByIdProductId(id)
				.orElseThrow(() -> new IllegalArgumentException("the record is not found"));
		productRepository.delete(product);
		return "delete is Successfuly";
	}
	
	public  Boolean existsByDepartmentId(Long departmentId) {
		return productRepository.existsByDepartmentIdDepartmentId(departmentId);
	}
	public Boolean existsByBrandId(Long brandId) {
		return productRepository.existsByBrandId(brandId);
	}
}

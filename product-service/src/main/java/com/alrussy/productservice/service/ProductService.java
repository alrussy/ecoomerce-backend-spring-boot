package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.dto.product_dto.ProductRequest;
import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.Product;
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
	
	public String delete(Long id) {
		 productRepository.deleteById(id);
		 return "delete is Successfuly";
	}
	
	public List<ProductResponse> filter(ProductFilter filter ){
		ProductSpecification productSpecification=new ProductSpecification(filter);
		return productRepository.findAll(productSpecification).stream().map(producr-> producr.mapToproductResponse()).toList();
		
	}
}

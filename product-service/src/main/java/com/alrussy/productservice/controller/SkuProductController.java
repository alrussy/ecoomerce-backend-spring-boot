package com.alrussy.productservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.productservice.dto.sku_product_dto.SkuProductRequest;
import com.alrussy.productservice.dto.sku_product_dto.SkuProductResponse;
import com.alrussy.productservice.service.SkuProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/sku")
@RequiredArgsConstructor

public class SkuProductController {

	private final SkuProductService skuProductService;

	@GetMapping
	public ResponseEntity<List<SkuProductResponse>> findAll() {
		return ResponseEntity.ok(skuProductService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SkuProductResponse> findById(@PathVariable String skuCode) {
		return ResponseEntity.ok(skuProductService.findById(skuCode));
	}

	@PostMapping
	public ResponseEntity<SkuProductResponse> save(@RequestBody SkuProductRequest request) {
		return ResponseEntity.ok(skuProductService.save(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SkuProductResponse> update(String skuCode, @RequestBody SkuProductRequest request) {
		return ResponseEntity.ok(skuProductService.update(skuCode,request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> postMethodName(@PathVariable String skuCode) {
		return ResponseEntity.ok(skuProductService.delete(skuCode));
	}

}

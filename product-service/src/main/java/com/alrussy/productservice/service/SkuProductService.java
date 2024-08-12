package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.dto.sku_product_dto.SkuProductRequest;
import com.alrussy.productservice.dto.sku_product_dto.SkuProductResponse;
import com.alrussy.productservice.entity.Details;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.SkuProduct;
import com.alrussy.productservice.entity.id.ProductId;
import com.alrussy.productservice.repository.SkuProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkuProductService {

	private final SkuProductRepository skuProductRepository;

	public List<SkuProductResponse> findAll() {

		return skuProductRepository.findAll().stream().map(SkuProduct::mapToSkuProductResponse).toList();
	}

	public SkuProductResponse findById(String id) {

		return skuProductRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("DetailsValue  By ID = " + id + " Is Not Found"))
				.mapToSkuProductResponse();
	}

	@Transactional
	public SkuProductResponse save(SkuProductRequest skuProductRequest) {
		
			return skuProductRepository.save(skuProductRequest.mapToSkuProduct()).mapToSkuProductResponse();
		
	}

	public String delete(String sku) {
		skuProductRepository.deleteById(sku);
		return "Delete By Id "+sku+"Is Success";
	}

	@Transactional
	public SkuProductResponse update(String sku, SkuProductRequest skuRequest) {

		SkuProduct skuFind = skuProductRepository.findById(sku)
				.orElseThrow(() -> new IllegalArgumentException("sku-product  By skuCode = " + sku + " Is Not Found "));
		if (skuRequest.getCategoryId() != null && skuRequest.getProductId() != null) {
			skuFind.setProduct(Product.builder().id(ProductId.builder().categoryId(skuRequest.getCategoryId())
					.productId(skuRequest.getProductId()).build()).build());
		}

		if (skuRequest.getDetailsIds() != null && !skuRequest.getDetailsIds().isEmpty()) {
				skuFind.getDetails().clear();
			
			skuRequest.getDetailsIds().stream().forEach(detailsId->{
			
			skuFind.addDetail(Details.builder().id(detailsId).build());
			});
		}

		return skuProductRepository.save(skuFind).mapToSkuProductResponse();
	}

	public List<SkuProductResponse> findByProductId(Long id) {
		return skuProductRepository.findByProductIdProductId(id).stream().map(SkuProduct::mapToSkuProductResponse).toList();
	}

}

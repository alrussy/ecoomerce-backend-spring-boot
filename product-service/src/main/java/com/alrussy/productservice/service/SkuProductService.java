package com.alrussy.productservice.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.dto.sku_product_dto.SkuProductRequest;
import com.alrussy.productservice.dto.sku_product_dto.SkuProductResponse;
import com.alrussy.productservice.entity.Details;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.SkuProduct;
import com.alrussy.productservice.entity.id.DetailsId;
import com.alrussy.productservice.entity.id.ProductId;
import com.alrussy.productservice.repository.SkuProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class SkuProductService {

	private final SkuProductRepository skuProductRepository;

	public List<SkuProductResponse> findAll() {

		return skuProductRepository.findAll().stream().map(SkuProduct::mapToSkuProductResponseWithPrduct).toList();
	}

	public SkuProductResponse findById(String skuCode) {
		return skuProductRepository.findById(skuCode)
				.orElseThrow(() -> new IllegalArgumentException("Sku Product  By ID = " + skuCode + " Is Not Found"))
				.mapToSkuProductResponseWithPrduct();
	}

	public List<SkuProductResponse> findByProduct(Long productId) {
		return findByProductId(productId).stream().map(SkuProduct::mapToSkuProductResponseOutPrduct).toList();
	}

	@Transactional
	public SkuProductResponse save(SkuProductRequest skuProductRequest) {
		List<SkuProductResponse> skuProductResponses = findByProductId(skuProductRequest.getProductId()).stream()
				.map(SkuProduct::mapToSkuProductResponseWithPrduct).toList();
		List<List<Long>> ids = skuProductResponses.stream()
				.map(sku -> sku.getDetails().stream().map(d -> d.getId()).toList()).toList();

		if (ids.stream().anyMatch(
				i -> i.containsAll(skuProductRequest.getDetailsIds().stream().map(d -> d.getDetailsId()).toList()))

		)
			throw new IllegalArgumentException("all details are exists with Product");

		if (skuProductResponses.stream().findFirst().get().getProduct().category().detailsName()
				.size() != skuProductRequest.getDetailsIds().size()) {
			throw new IllegalArgumentException("must add all details");
		}
		skuProductRepository.save(skuProductRequest.mapToSkuProduct());

		return new SkuProductResponse();

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

			skuRequest.getDetailsIds().stream().forEach(detailsId -> {

				skuFind.addDetail(Details.builder().id(detailsId).build());
			});
		}

		return skuProductRepository.save(skuFind).mapToSkuProductResponseWithPrduct();
	}

	public String delete(String sku) {
		skuProductRepository.deleteById(sku);
		return "Delete By Id " + sku + "Is Success";
	}

	public SkuProductResponse testSave() {
		SkuProductRequest request = SkuProductRequest.builder().categoryId(605L).productId(752L)
				.detailsIds(List.of(DetailsId.builder().detailsId(3L).detailsNameId(2L).build(),
						DetailsId.builder().detailsId(1L).detailsNameId(1L).build()))
				.build();
		return save(request);
	}
	
	public String testdelete(String id) {
		
		return delete(id);
	}
	
	
	private List<SkuProduct> findByProductId(Long id) {
		return skuProductRepository.findByProductIdProductId(id);
	}

}

package com.alrussy.productservice.dto.sku_product_dto;

import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.SkuProduct;
import com.alrussy.productservice.entity.id.CategoryDetailsNameId;
import com.alrussy.productservice.entity.id.DetailsId;
import com.alrussy.productservice.entity.id.ProductId;
import com.alrussy.productservice.entity.table.CategoryDetailsName;

import java.util.ArrayList;
import java.util.List;

import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Details;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkuProductRequest {
	
	private Long productId;
	private Long categoryId;
	private List<DetailsId> detailsIds;

	
	public SkuProduct mapToSkuProduct() {
		
		List<CategoryDetailsName> categoryDetailsNames=new ArrayList<>();
		List<Details> details=new ArrayList<>();
		
		if(detailsIds!=null)
		detailsIds.stream().forEach(
				id->{
					details.add(
					Details.builder().id(id).build());
					categoryDetailsNames.add(CategoryDetailsName.builder().
							id(CategoryDetailsNameId.builder().detailsnameId(categoryId).detailsnameId(id.getDetailsNameId()).build()).build());
				
				
				});
		return SkuProduct.builder()
				.product(Product.builder().id(ProductId.builder().categoryId(categoryId).productId(productId).build()).build())
				.details( details)
				.categoryDetailsNames(categoryDetailsNames)
				.build();
		}
}

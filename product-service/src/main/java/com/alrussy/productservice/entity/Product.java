package com.alrussy.productservice.entity;

import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private Double price;
	private Boolean isActivity;

	@ManyToOne
	private BrandCategory brandCategory;

	public ProductResponse mapToproductResponse() {
		return new ProductResponse(
				id, 
				name,
				price, 
				isActivity,
				brandCategory.getBrandCategoryId().getCategory().mapToCategoryResponse(),
				brandCategory.getBrandCategoryId().getBrand().mapToBrandResponse());
		
	}

}

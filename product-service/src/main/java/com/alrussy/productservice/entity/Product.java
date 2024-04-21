package com.alrussy.productservice.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "products")
@EntityListeners(AuditingEntityListener.class)
public class Product extends Audition{

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

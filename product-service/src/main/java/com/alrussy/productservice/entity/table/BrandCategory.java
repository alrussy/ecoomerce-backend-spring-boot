package com.alrussy.productservice.entity.table;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.CategoryBrandId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "category_brand")

public class BrandCategory {
	
	@EmbeddedId
	private CategoryBrandId id;
	
	@ManyToOne
	@JoinColumn(name = "categoryId",insertable = false,updatable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "brandId",insertable = false,updatable = false)
	private Brand brand;
	
	

}

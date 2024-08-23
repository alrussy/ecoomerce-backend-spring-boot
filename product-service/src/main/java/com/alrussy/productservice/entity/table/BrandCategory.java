package com.alrussy.productservice.entity.table;

import java.util.List;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
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
@Table(name = "brand_category")

public class BrandCategory {
	
	@EmbeddedId
	private BrandCategoryId brandCategoryId;
	
	@ManyToOne
	@JoinColumn(name = "categoryId",updatable = false,insertable = false)
	//@MapsId("categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "brandId",updatable = false,insertable = false)
	//@MapsId("brandId")
	private Brand brand;

}

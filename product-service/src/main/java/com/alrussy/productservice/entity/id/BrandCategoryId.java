package com.alrussy.productservice.entity.id;

import java.io.Serializable;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor


public class BrandCategoryId implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	
	@ManyToOne
	@JoinColumn(name = "brandId")
	private Brand brand;
	


}

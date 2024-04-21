package com.alrussy.productservice.entity.table;

import com.alrussy.productservice.entity.id.BrandCategoryId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
}

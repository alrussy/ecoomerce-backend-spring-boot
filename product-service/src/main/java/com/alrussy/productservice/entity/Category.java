package com.alrussy.productservice.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.dto.category_dto.CategoryResponse;
import com.alrussy.productservice.entity.table.BrandCategory;
import com.alrussy.productservice.entity.table.CategoryDetailsName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
@EntityListeners(AuditingEntityListener.class)

public class Category extends Audition {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String imageUrl;
	private Boolean isFeature;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = BrandCategory.class)
	private List<BrandCategory> brandCategory;

	@OneToMany(mappedBy = "category", targetEntity = CategoryDetailsName.class)
	private List<CategoryDetailsName> categoryDetailsNames;

	public CategoryResponse mapToCategoryResponseWithBrandOutDetailsName() {

		return new CategoryResponse(id, name, isFeature, imageUrl,
				getBrandCategory() != null
						? getBrandCategory().stream().map(bc -> bc.getBrand().mapToBrandResponseOutCategory()).toList()
						: null,
				null);
	}

	public CategoryResponse mapToCategoryResponseOutDetailsNameAndBrand() {

		return new CategoryResponse(id, name, isFeature, imageUrl,null,null);
	}

	public CategoryResponse mapToCategoryResponseWithDetailsNameOutBrand() {
		return new CategoryResponse(id, name, isFeature, imageUrl, null, getCategoryDetailsNames().stream()
				.map(cd -> cd.getDetailsName().mapToDetailsNameResponseOutValues()).toList());
	}

	public CategoryResponse mapToCategoryResponseWithDetailsNameAndBrand() {
		return new CategoryResponse(id, name, isFeature, imageUrl,
				getBrandCategory() != null
						? getBrandCategory().stream().map(bc -> bc.getBrand().mapToBrandResponseOutCategory()).toList()
						: null,
				getCategoryDetailsNames().stream().map(cd -> cd.getDetailsName().mapToDetailsNameResponseOutValues())
						.toList());
	}
}

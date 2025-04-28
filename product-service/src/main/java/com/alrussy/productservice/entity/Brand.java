package com.alrussy.productservice.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.dto.brand_dto.BrandResponse;
import com.alrussy.productservice.dto.category_dto.CategoryResponse;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "brands")
@EntityListeners(AuditingEntityListener.class)
public class Brand extends Audition{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String imageUrl;

	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
	private List<BrandCategory> brandCategories;
	

	public BrandResponse mapToBrandResponse() {
		return new BrandResponse(id,name,imageUrl,
				brandCategories.stream().map( category ->
				category.getCategory().mapToCategoryResponse()).toList());
				
	}

	

}

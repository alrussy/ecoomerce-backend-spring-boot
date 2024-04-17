package com.alrussy.productservice.entity;

import java.util.List;
import java.util.Set;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.JdbcTypeRegistration;

import com.alrussy.productservice.dto.brand_dto.BrandResponse;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Entity
@Table(name = "brands")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String imageUrl;

	@OneToMany(cascade = CascadeType.MERGE, targetEntity = BrandCategory.class)
	@JoinColumn(name = "brandId")
	private List<BrandCategory> categories;
	
	
	public BrandResponse mapToBrandResponsewithCategories() {
		return BrandResponse
				.builder()
				.id(id)
				.name(name)
				.imageUrl(imageUrl)
				.categories(categories!=null?categories.stream().map(t ->t.getBrandCategoryId().getCategory().mapToCategoryResponse()).toList():null)
				.build();
	}

	public BrandResponse mapToBrandResponse() {
		return BrandResponse
				.builder()
				.id(id)
				.name(name)
				.imageUrl(imageUrl)
				.build();
	}


}

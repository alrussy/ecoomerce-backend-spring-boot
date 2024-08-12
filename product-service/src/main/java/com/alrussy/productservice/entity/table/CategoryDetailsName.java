package com.alrussy.productservice.entity.table;

import java.util.List;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.entity.Product;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.CategoryDetailsNameId;

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
@Table(name = "category_details_name")

public class CategoryDetailsName {
	
	@EmbeddedId
	private CategoryDetailsNameId id;
	
	@ManyToOne
	@JoinColumn(name = "category_id",updatable = false,insertable = false )
	@MapsId("categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "details_name_id",updatable = false,insertable = false )
	@MapsId("detailsnameId")
	private DetailsName detailsName;
	
	

}

package com.alrussy.productservice.entity;

import java.util.List;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.id.ProductId;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
public class Product extends Audition {

	@EmbeddedId
	private ProductId id;
	private String name;
	private Double price;
	private Double discount;
	private Boolean isActivity;
	private Boolean isFeature;
	private String currency;
	@Transient
	Double priceAfterDiscount;	
	

	@ElementCollection
	private List<String> imageUrls;
	
	
	@ManyToOne
	@JoinColumnsOrFormulas(value = {
	    @JoinColumnOrFormula(formula = @JoinFormula(value = "category_id")),
	    @JoinColumnOrFormula(column = @JoinColumn(name= "department_id" ))
	    
	})
	//@ManyToOne
	//@JoinColumns(  {@JoinColumn(name= "department_id" ),@JoinColumn(name= "categoryId",insertable=false, updatable=false)})
	private Department department;
	@ManyToOne
	@JoinColumnsOrFormulas(value = {
	    @JoinColumnOrFormula(formula = @JoinFormula(value = "category_id")),
	    @JoinColumnOrFormula(column = @JoinColumn(name= "brand_id" ))
	    
	})

//	@ManyToOne
	//@JoinColumns(  {@JoinColumn(name= "brand_id"),@JoinColumn(name= "categoryId",insertable=false, updatable=false)})
	private BrandCategory brandCategory;
	@PostLoad
	public void setPriceAfterDiscount() {
		priceAfterDiscount = price-price*discount/100;
	}
	
	public ProductResponse mapToproductResponse() {
		return new ProductResponse(
				id.getProductId(), name, price,discount,priceAfterDiscount, isActivity,
				brandCategory.getCategory().mapToCategoryResponse(),
				department.mapToDepartmentResponse(),null);						

	}

}

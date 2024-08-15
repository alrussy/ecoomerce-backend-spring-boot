package com.alrussy.productservice.entity;

import java.util.List;

import org.hibernate.annotations.JoinColumnOrFormula;
import org.hibernate.annotations.JoinColumnsOrFormulas;
import org.hibernate.annotations.JoinFormula;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.dto.product_dto.ProductResponse;
import com.alrussy.productservice.entity.id.ProductId;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "products",uniqueConstraints = @UniqueConstraint(columnNames = {"name","brand_id","category_id","department_id"}))
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
	private Double priceAfterDiscount;	
	@ElementCollection
	private List<String> imageUrls;
	private Long brandId;
	
	
	@ManyToOne
	@JoinColumn(name = "brandId",insertable = false,updatable = false)
private Brand brand;
	@ManyToOne
	@JoinColumnsOrFormulas(
			value = {
					@JoinColumnOrFormula(formula = @JoinFormula(value = "category_id")),
					@JoinColumnOrFormula(column = @JoinColumn(name = "department_id")) 
					}
			)
	private Department department;
	@ManyToOne
	@JoinColumnsOrFormulas(
			value = { 
					@JoinColumnOrFormula(formula = @JoinFormula(value = "category_id")),
					@JoinColumnOrFormula(formula = @JoinFormula(value = "brand_id"))
					}
			)
	private BrandCategory brandCategory;

	
	@PostLoad
	public void setPriceAfterDiscount() {
		priceAfterDiscount = price - price * discount / 100;
	}
	public ProductResponse mapToproductResponseWithCategoryBrandAndDepartment() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				department.getCategory().mapToCategoryResponseOutDetailsNameAndBrand(),//category
				department.mapToDepartmentResponseOutCategory(),//department
				brand!=null?brand.mapToBrandResponseOutCategory():brandCategory.getBrand().mapToBrandResponseOutCategory()//brand
				);
		}
	public ProductResponse mapToproductResponseOutCategory() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				null,//category
				department.mapToDepartmentResponseOutCategory(),//department
				brandCategory.getBrand().mapToBrandResponseOutCategory()//brand
				);
		}
	public ProductResponse mapToproductResponseOutCategoryBrandAndDepartment() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				null,//category
				null,//department
				null//brand
				);
		}
	public ProductResponse mapToproductResponseOutBrand() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				department.getCategory().mapToCategoryResponseOutDetailsNameAndBrand(),//category
				department.mapToDepartmentResponseOutCategory(),//department
				null//brand
				);
		}
	public ProductResponse mapToproductResponseOutDepartment() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				department.getCategory().mapToCategoryResponseOutDetailsNameAndBrand(),//category
				null,//department
				brandCategory.getBrand().mapToBrandResponseOutCategory()//brand
				);
		}
	public ProductResponse mapToproductResponseWithDepartment() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				null,//category
				department.mapToDepartmentResponseOutCategory(),//department
				null//brand
				);
		}
	public ProductResponse mapToproductResponseWithBrand() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				null,//category
				null,//department
				brandCategory.getBrand().mapToBrandResponseOutCategory()//brand
				);
		}
	public ProductResponse mapToproductResponseWithCategory() {
		return new ProductResponse(
				id.getProductId(), 
				name, 
				price,
				discount, 
				priceAfterDiscount,
				isActivity,
				isFeature,
				imageUrls,
				department.getCategory().mapToCategoryResponseWithDetailsNameOutBrand(),//category
				null,//department
				null//brand
				);
		}


}

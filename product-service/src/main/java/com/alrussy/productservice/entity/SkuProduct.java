package com.alrussy.productservice.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.entity.id.SkuProductId;
import com.alrussy.productservice.entity.table.CategoryDetailsName;

import jakarta.persistence.CascadeType;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@Table(name = "sku-products")
@EntityListeners(AuditingEntityListener.class)
public class SkuProduct extends Audition {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String skuCode;
	
	@ManyToOne
	@JoinColumns({@JoinColumn(name="productId"),@JoinColumn(name="categoryId")})
	private Product product;
	
	@OneToMany
	private List<Details> details;
	
	@OneToMany
	private List<CategoryDetailsName>  categoryDetailsNames;
	
	
	

}

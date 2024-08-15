package com.alrussy.productservice.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.dto.sku_product_dto.SkuProductResponse;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
	@JoinColumns({ @JoinColumn(name = "productId"), @JoinColumn(name = "categoryId") })
	private Product product;
	@Builder.Default
	@ManyToMany
	@JoinTable(name = "sku-details", joinColumns = @JoinColumn(name = "skuCode"), uniqueConstraints = @UniqueConstraint(columnNames = {
			"sku_code", "detailsNameId" }), inverseJoinColumns = { @JoinColumn(name = "detailsId"),
					@JoinColumn(name = "detailsNameId") })
	private List<Details> details = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "sku-details_names", joinColumns = @JoinColumn(name = "sku_code"), inverseJoinColumns = {
			@JoinColumn(name = "categoryId"), @JoinColumn(name = "detailsNameId") })
	private List<CategoryDetailsName> categoryDetailsNames;

	public void addDetail(Details detail) {
		details.add(detail);
	}

	public void removeDetail(Details detail) {
		details.remove(detail);
	}

	public SkuProductResponse mapToSkuProductResponseWithPrduct() {
		return SkuProductResponse.builder().skuCode(skuCode)
				.product(product.mapToproductResponseWithCategory())
				.details(details != null ? details.stream().map(Details::mapToDetailsResponse).toList() : null).build();
	}

	public SkuProductResponse mapToSkuProductResponseOutPrduct() {
		return SkuProductResponse.builder().skuCode(skuCode)
				.details(details != null ? details.stream().map(Details::mapToDetailsResponse).toList() : null).build();
	}

}

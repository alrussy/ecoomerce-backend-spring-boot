package com.alrussy.productservice.entity.id;

import java.io.Serializable;

import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.Category;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@EqualsAndHashCode
public class ProductId implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -293757139257770506L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private Long categoryId;
	

}

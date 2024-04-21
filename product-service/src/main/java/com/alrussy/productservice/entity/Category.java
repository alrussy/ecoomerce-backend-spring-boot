package com.alrussy.productservice.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.dto.category_dto.CategoryResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

	
	public CategoryResponse mapToCategoryResponse() {
		return CategoryResponse
				.builder()
				.id(id)
				.name(name)
				.imageUrl(imageUrl)
				.build();
	}
	
}

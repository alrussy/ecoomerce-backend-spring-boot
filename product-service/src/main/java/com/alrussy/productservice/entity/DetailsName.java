package com.alrussy.productservice.entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.dto.details_name_dto.DetailsNameResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "detailsNames")
@EntityListeners(AuditingEntityListener.class)
public class DetailsName extends Audition{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String detailsName;
	@OneToMany(mappedBy = "detailsName",orphanRemoval = true)
	private List<Details> values;
	
	
	
	public DetailsNameResponse mapToDetailsNameResponseWithValues() {
		return DetailsNameResponse.builder()
				.id(id)
				.name(detailsName)
				.values(values!=null?values.stream().map(Details::mapToDetailsResponse).toList():null)
				.build();
	}
	public DetailsNameResponse mapToDetailsNameResponseOutValues() {
		return DetailsNameResponse.builder()
				.id(id)
				.name(detailsName)
				.build();
	}
}

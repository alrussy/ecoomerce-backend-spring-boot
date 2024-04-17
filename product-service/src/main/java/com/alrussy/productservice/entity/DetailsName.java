package com.alrussy.productservice.entity;

import java.util.List;

import com.alrussy.productservice.dto.details_name_dto.DetailsNameResponse;
import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detailsNamee")
public class DetailsName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String detailsName;
	
	@OneToMany(mappedBy = "detailsName",orphanRemoval = true)
	private List<DetailsValue> values;

	
	public DetailsNameResponse mapToDetailsNameResponse() {
		return DetailsNameResponse.builder()
				.id(id)
				.name(detailsName)
				.values(values!=null?values.stream().map(DetailsValue::mapToDetailsValueResponse).toList():null)
				.build();
	}
}

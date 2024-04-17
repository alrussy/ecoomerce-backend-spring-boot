package com.alrussy.productservice.entity;

import java.util.List;

import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;
import com.alrussy.productservice.entity.table.BrandCategory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "detailsValue")
public class DetailsValue {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String detailsValue;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "detailsNameId")
	private DetailsName detailsName;
	
	
	public DetailsValueResponse mapToDetailsValueResponse(){
		return DetailsValueResponse.builder()
				.id(id)
				.value(detailsValue)
				.name(detailsName.getDetailsName())
				.build(); 
	}
	

}

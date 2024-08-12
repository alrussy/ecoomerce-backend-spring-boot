package com.alrussy.productservice.dto.details_dto;

import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.entity.id.DetailsId;
import com.alrussy.productservice.entity.Details;

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
public class DetailsRequest {
	
	private String value;
	private Long detailsNameId;

	
	public Details mapToDetailsValue() {
		return Details.builder()
				.id(DetailsId.builder().detailsNameId(detailsNameId).build())
				.value(value)
				.detailsName(DetailsName.builder().id(detailsNameId).build())
				.build();
		}
}

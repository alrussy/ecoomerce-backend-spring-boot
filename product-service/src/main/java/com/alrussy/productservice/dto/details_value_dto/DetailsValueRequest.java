package com.alrussy.productservice.dto.details_value_dto;

import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.entity.DetailsValue;

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
public class DetailsValueRequest {
	
	private String value;
	private Long detailsNameId;

	
	public DetailsValue mapToDetailsValue() {
		return DetailsValue.builder()
				.detailsValue(value)
				.detailsName(DetailsName.builder().id(detailsNameId).build())
				.build();
		}
}

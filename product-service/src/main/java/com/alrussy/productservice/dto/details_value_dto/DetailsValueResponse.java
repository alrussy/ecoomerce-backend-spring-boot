package com.alrussy.productservice.dto.details_value_dto;

import com.alrussy.productservice.dto.details_name_dto.DetailsNameResponse;

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
public class DetailsValueResponse {

	private Long id;
	private String value;
	private String name;
}

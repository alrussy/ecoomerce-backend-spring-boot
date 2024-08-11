package com.alrussy.productservice.dto.details_name_dto;

import java.util.List;

import com.alrussy.productservice.dto.details_dto.DetailsResponse;

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
public class DetailsNameResponse {

	private Long id;
	private String name;
	private List<DetailsResponse> values;
}

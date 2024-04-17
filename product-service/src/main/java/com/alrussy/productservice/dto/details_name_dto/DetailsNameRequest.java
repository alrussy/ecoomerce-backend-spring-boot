package com.alrussy.productservice.dto.details_name_dto;

import java.util.List;

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
public class DetailsNameRequest {

	private String name;

	public DetailsName mapToDetailsName() {
		return DetailsName.builder().detailsName(name)
				.build();
	}
}

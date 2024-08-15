package com.alrussy.productservice.dto.category_dto;

import java.util.List;

import com.alrussy.productservice.dto.brand_dto.BrandResponse;
import com.alrussy.productservice.dto.details_name_dto.DetailsNameResponse;

public record CategoryResponse(Long id, String name, Boolean isFeature, String imageUrl,List<BrandResponse> brands,List<DetailsNameResponse> detailsName) {
}

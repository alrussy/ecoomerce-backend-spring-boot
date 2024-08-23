package com.alrussy.productservice.dto.department_dto;

import com.alrussy.productservice.dto.category_dto.CategoryResponse;

public record DepartmentResponse(Long id,String name,CategoryResponse category) {
	
	
	

}

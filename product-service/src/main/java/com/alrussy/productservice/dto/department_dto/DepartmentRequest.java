package com.alrussy.productservice.dto.department_dto;

import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.id.DepartmentId;

public record DepartmentRequest(String name ,Long categoryId){

	public Department mapToDepartment() {
		return Department.builder().id(DepartmentId.builder().categoryId(categoryId).build()).name(name).category(Category.builder().id(categoryId).build()).build();
	}
}

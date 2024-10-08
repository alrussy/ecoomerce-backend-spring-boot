package com.alrussy.productservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alrussy.productservice.dto.department_dto.DepartmentRequest;
import com.alrussy.productservice.dto.department_dto.DepartmentResponse;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.id.DepartmentId;
import com.alrussy.productservice.repository.DepartmentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentService {

	private final DepartmentRepository departmentRepository;
	private final ProductService productService;

	public List<DepartmentResponse> findAll() {

		return departmentRepository.findAll().stream()
				.map(department -> department.mapToDepartmentResponseWithCategory()).toList();
	}

	public DepartmentResponse findById(Long id) {

		return departmentRepository.findByIdDepartmentId(id)
				.orElseThrow(() -> new IllegalArgumentException("Department  By ID = " + id + " Is Not Found"))
				.mapToDepartmentResponseWithCategory();
	}

	public DepartmentResponse save(DepartmentRequest departmentRequest) {
		
		return departmentRepository.save(departmentRequest.mapToDepartment()).mapToDepartmentResponseOutCategory();

	}

	public void delete(Long id) {
		if(!productService.existsByDepartmentId(id)) {
			
			Department department = departmentRepository.findByIdDepartmentId(id)
					.orElseThrow(() -> new IllegalArgumentException("Department  By ID = " + id + " Is Not Found "));
			;
			departmentRepository.delete(department);
		}
		else {
			throw  new IllegalArgumentException("can't delete Department  By ID = " + id + " department is refreance with products");
		}
	}

	public DepartmentResponse update(Long id, DepartmentRequest departmentRequest) {

		Department departmetResponseFind = departmentRepository.findByIdDepartmentId(id).orElseThrow(() -> new IllegalArgumentException("Department  By ID = " + id + " Is Not Found "));

		if (departmentRequest.name() != null) {
			departmetResponseFind.setName(departmentRequest.name());
		}

		if (departmentRequest.categoryId() != null) {

			departmetResponseFind.setCategory(Category.builder().id(departmentRequest.categoryId()).build());
		}

		return departmentRepository.save(departmetResponseFind).mapToDepartmentResponseOutCategory();
	}

}

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

	public List<DepartmentResponse> findAll() {

		return departmentRepository.findAll().stream()
				.map(department -> department.mapToDepartmentResponseOutCategory()).toList();
	}

	public DepartmentResponse findById(DepartmentId id) {

		return departmentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("DetailsValue  By ID = " + id + " Is Not Found"))
				.mapToDepartmentResponseOutCategory();
	}

	public DepartmentResponse save(DepartmentRequest departmentRequest) {
		
		if(departmentRepository.existsByName(departmentRequest.name())) {
			throw  new IllegalArgumentException("A Department is Exist By name = " + departmentRequest.name()  );
		}
		return departmentRepository.save(departmentRequest.mapToDepartment()).mapToDepartmentResponseOutCategory();

	}

	public void delete(Long id) {
		Department department = departmentRepository.findByIdDepartmentId(id)
				.orElseThrow(() -> new IllegalArgumentException("Department  By ID = " + id + " Is Not Found "));
		;
		departmentRepository.delete(department);
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

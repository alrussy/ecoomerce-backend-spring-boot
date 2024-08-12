package com.alrussy.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.productservice.entity.Department;
import com.alrussy.productservice.entity.id.DepartmentId;

public interface DepartmentRepository extends JpaRepository<Department, DepartmentId> {

	
	Optional<Department> findByIdDepartmentId(Long id);
	
	Boolean existsByName(String name);

}

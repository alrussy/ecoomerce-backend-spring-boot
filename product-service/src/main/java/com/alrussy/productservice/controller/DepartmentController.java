package com.alrussy.productservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.productservice.dto.department_dto.DepartmentRequest;
import com.alrussy.productservice.dto.department_dto.DepartmentResponse;
import com.alrussy.productservice.entity.id.DepartmentId;
import com.alrussy.productservice.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products/departments")
@RequiredArgsConstructor
public class DepartmentController {

	private final DepartmentService departmentService;
	
	
	@GetMapping
	public ResponseEntity<List<DepartmentResponse>> findAll(){
		return ResponseEntity.ok(departmentService.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<DepartmentResponse> findById(@PathVariable Long id){
		return ResponseEntity.ok(departmentService.findById(id));
	}

	@PostMapping
	public ResponseEntity<DepartmentResponse> save(@RequestBody DepartmentRequest request){
		return ResponseEntity.ok(departmentService.save(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<DepartmentResponse> update(@PathVariable Long id ,@RequestBody DepartmentRequest request){
		return ResponseEntity.ok(departmentService.update(id,request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		departmentService.delete(id);
	return ResponseEntity.ok("delete By "+id+" is successfuly");
	}

	
	
}

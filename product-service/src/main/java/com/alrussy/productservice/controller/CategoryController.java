package com.alrussy.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alrussy.productservice.dto.category_dto.CategoryRequest;
import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

	private final CategoryService categoryService;
	
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(categoryService.findAll());
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody CategoryRequest category){
		return ResponseEntity.ok(categoryService.save(category));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id ,@RequestBody CategoryRequest category){
		return ResponseEntity.ok(categoryService.update(id,category));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		categoryService.delete(id);
	return ResponseEntity.ok("delete By "+id+" is successfuly");
	}
	
	
}

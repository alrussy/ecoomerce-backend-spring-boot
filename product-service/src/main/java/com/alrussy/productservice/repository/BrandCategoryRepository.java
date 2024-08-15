package com.alrussy.productservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.productservice.entity.Category;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.table.BrandCategory;

public interface BrandCategoryRepository extends JpaRepository<BrandCategory, BrandCategoryId> {

	
}


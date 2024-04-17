package com.alrussy.productservice.repository.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.alrussy.productservice.dto.product_dto.PriceFilter;
import com.alrussy.productservice.dto.product_dto.ProductFilter;
import com.alrussy.productservice.entity.Product;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSpecification implements Specification<Product> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductFilter filter;
	
	@Override
	public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	List<Predicate> predicates = new ArrayList<>();
		if(filter.name()!=null) {
		  predicates.add(criteriaBuilder.like(root.get("name"), filter.name()));
		}
		if(filter.brandId()!=null) {
			  predicates.add(criteriaBuilder.equal(root.get("brandCategory").get("brandCategoryId").get("brand").get("id"), filter.brandId()));
			}
		if(filter.categoryId()!=null) {
			  predicates.add(criteriaBuilder.equal(root.get("brandCategory").get("brandCategoryId").get("category").get("id"), filter.categoryId()));
			}
		if(filter.price()!=null){
			if(filter.price().type() == PriceFilter.TypeFilter.EQUEL) {
				 predicates.add(criteriaBuilder.equal(root.get("price"),filter.price().price()));
			}
			else if(filter.price().type() == PriceFilter.TypeFilter.GREATE_THAN) {
				 predicates.add(criteriaBuilder.greaterThan(root.get("price"),filter.price().price()));
			}
			else if(filter.price().type() == PriceFilter.TypeFilter.GREATE_THAN_OR_EQUEL) {
				 predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"),filter.price().price()));
			}
			else if(filter.price().type() == PriceFilter.TypeFilter.LESS_THAN_OR_EQUEL) {
				 predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"),filter.price().price()));
			}
			else
				 predicates.add(criteriaBuilder.lessThan(root.get("price"),filter.price().price()));
				
		}
		
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}

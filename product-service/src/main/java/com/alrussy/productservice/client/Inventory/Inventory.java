package com.alrussy.productservice.client.Inventory;

import java.util.List;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;
import com.alrussy.productservice.entity.DetailsValue;
import com.alrussy.productservice.entity.Product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
	
	private Long id;
	private Long productId;
	private Integer quentity;
	private List<DetailsValueResponse> values;
	
	

}

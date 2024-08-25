package com.alrussy.order_service.dto;

import com.alrussy.order_service.model.OrderLineProducts;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineProductsDto {

	private String skuCode;
    private Double price;
    private Double discount;
    private Integer quentity;

    
    
    
    public OrderLineProducts mapToOrderLineProducts() {
    	return OrderLineProducts.builder().skuCode(skuCode).price(price).discount(discount).quentity(quentity).build();
    }
}

package com.alrussy.order_service.dto;
import java.math.BigDecimal;

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
public class OrderLineItemsDto {

	private Long inventoryId;
    private BigDecimal price;
    private Integer quentity;


    
    public OrderLineProducts mapToOrderLineItems() {
        return OrderLineProducts.builder()
                .inventoryId(inventoryId)
                .price(price)
                .quentity(quentity)
                .build();
    }
}

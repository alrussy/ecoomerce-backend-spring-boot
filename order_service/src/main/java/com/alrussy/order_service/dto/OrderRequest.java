package com.alrussy.order_service.dto;
import java.util.List;
import java.util.UUID;

import com.alrussy.order_service.model.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

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
public class OrderRequest {
	
  private String ordeNumber;
   @JsonProperty("card")
  private List<OrderLineItemsDto> card;




    public Order mapToOrderRequest(){
        return Order.builder().orderNumber(ordeNumber)
        .card(card.stream().map(OrderLineItemsDto::mapToOrderLineItems).toList())
        .build();
    }
}

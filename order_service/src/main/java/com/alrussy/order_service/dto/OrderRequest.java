package com.alrussy.order_service.dto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.alrussy.order_service.model.Order;
import com.alrussy.order_service.model.enums.States;
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

	private Long addressId;
	private String paymentId;
	private Double total;

	@JsonProperty("card")
	private List<OrderLineProductsDto> orderLineProducts;

	public Order mapToOrderRequest() {
		return Order.builder().addressId(addressId).paymentId(paymentId).total(total).orderLineProducts(
				orderLineProducts != null
						? orderLineProducts.stream().map(OrderLineProductsDto::mapToOrderLineProducts).collect(Collectors.toSet())
						: null)
				.build();
	}
}

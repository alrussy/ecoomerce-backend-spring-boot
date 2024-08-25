package com.alrussy.order_service.model;

import com.alrussy.order_service.dto.OrderLineProductsDto;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Entity
@Table(name = "order_line_product")
public class OrderLineProducts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String skuCode;
    private Double price;
    private Double discount;
    private Integer quentity;
  

    public OrderLineProductsDto mapTOrderLineProductsResponseDto() {
        return OrderLineProductsDto.builder()
        		.skuCode(skuCode)
        		.price(price)
                .quentity(quentity)
                .build();
    }

}

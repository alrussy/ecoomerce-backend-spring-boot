package com.alrussy.order_service.model;

import java.math.BigDecimal;

import com.alrussy.order_service.dto.OrderLineItemsDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "order_line_items")
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long inventoryId;
    private BigDecimal price;
    private Integer quentity;
  

    public OrderLineItemsDto mapTOrderLineItemsResponseDto() {
        return OrderLineItemsDto.builder()
                .inventoryId(inventoryId)
                .price(price)
                .quentity(quentity)
                .build();
    }

}

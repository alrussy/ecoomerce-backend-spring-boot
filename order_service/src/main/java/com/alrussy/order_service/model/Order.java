package com.alrussy.order_service.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alrussy.order_service.model.enums.States;import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
@Table(name = "_orders")
public class Order {
	
	
    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private String orderNumber;
    private LocalDateTime orderDate;
    private String emailCostumer;
    private Long addressId;
    private String paymentId;
    private Double total; 
    @Enumerated(EnumType.STRING)
    private States status;
    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL})
    private Set<OrderLineProducts> orderLineProducts=new HashSet<>();
    
    public void addOrderLineProduct(OrderLineProducts lineProduct) {
    	orderLineProducts.add(lineProduct);
    	
    }
    public void removeOrderLineProduct(OrderLineProducts lineProduct) {
    	orderLineProducts.remove(lineProduct);
    	
    }
}

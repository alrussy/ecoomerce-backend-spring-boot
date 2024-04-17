package com.alrussy.inventory_service.model;

import java.util.List;
import java.util.Map;

import com.alrussy.inventory_service.client.product.DetailsProduct;
import com.alrussy.inventory_service.dto.InventoryResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "inventories")
public class Inventory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productId;
    private Integer quantity;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Details> details;
   
    @OneToMany(cascade = CascadeType.ALL)
    private List<ActionInventory> actionInventories;
    
    public void addAction(ActionInventory action){
    	 this.actionInventories.add(action);	 
    }
    
    
    
    public InventoryResponse mapTonventoryResponse(List<DetailsProduct> detailsProducts) {
    	return InventoryResponse.builder().id(id).productId(productId).quantity(quantity).details(detailsProducts).build();  
    }


}

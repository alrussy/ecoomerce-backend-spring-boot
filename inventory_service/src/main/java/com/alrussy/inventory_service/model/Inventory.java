package com.alrussy.inventory_service.model;


import java.util.HashSet;
import java.util.Set;

import com.alrussy.inventory_service.dto.InventoryResponse;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "inventory")
public class Inventory {


    @Id
    private String skuCode;
    private Integer quantity;
   
    @Builder.Default
    @OneToMany(cascade = CascadeType.PERSIST)
    private Set<ActionInventory> actionInventories=new HashSet();
    
    public void addAction(ActionInventory action){
    	 this.actionInventories.add(action);	 
    }
    public void removeAction(ActionInventory action){
   	 this.actionInventories.remove(action);	 
   }
    
    
    public InventoryResponse mapTonventoryResponse() {
    	return InventoryResponse.builder().skuCode(skuCode).quantity(quantity).actionInventories(
    			actionInventories.stream().toList())
    			.build();  
    }
    public InventoryResponse mapTonventoryResponseOutActions() {
    	return InventoryResponse.builder().skuCode(skuCode).quantity(quantity)
    			.build();  
    }


}

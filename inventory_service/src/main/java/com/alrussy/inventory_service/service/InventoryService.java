package com.alrussy.inventory_service.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.alrussy.inventory_service.client.product.DetailsProduct;
import com.alrussy.inventory_service.client.product.ProductService;
import com.alrussy.inventory_service.dto.InventoryOrder;
import com.alrussy.inventory_service.dto.InventoryRequest;
import com.alrussy.inventory_service.dto.InventoryResponse;
import com.alrussy.inventory_service.model.ActionInventory;
import com.alrussy.inventory_service.model.Details;
import com.alrussy.inventory_service.model.Inventory;
import com.alrussy.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    
    private final InventoryRepository repository;
    private final ProductService productService;


    public List<InventoryResponse> findAll() {
    	return repository.findAll().stream().map(inventory->inventory.mapTonventoryResponse(getDetailsProduct(inventory.getDetails()))).toList();
    }
    
    
    public String orderProducts(InventoryOrder order){
       order.getInventoryOrders().stream().forEach(inventory-> {
    	updateStoreItem(inventory.getInventoryId(),inventory.getQuentity(),"order");
    	});
       
       return "Order is Sucessfuly";
       
       
    }



    public String storeItem(InventoryRequest inventoryRequest) {
    	String numberAction=UUID.randomUUID().toString();
    	ActionInventory actionInventory= ActionInventory.builder().numberAction(numberAction).actionType("NewStore").build();
    	Inventory inventory=inventoryRequest.mapToInventory();
    	inventory.addAction(actionInventory);
        repository.save(inventory);
        return "store item is successfuly";
    }
    
    
    public String updateStoreItem(Long id,Integer quentity,String action) {
    	Inventory inventoryFind= repository.findById(id).orElseThrow();
    	String numberAction=UUID.randomUUID().toString();
    	int quentityNew=inventoryFind.getQuantity();
    	if(action.equals("order")) 	
    	quentityNew=quentityNew-quentity;
    	else 
    		quentityNew=quentityNew+quentity;
    	
    	if(quentityNew >=0) {
        	inventoryFind.setQuantity(quentityNew);
        	ActionInventory actionInventory= ActionInventory.builder().numberAction(numberAction).actionType(action).quentity(quentity).build();
        	inventoryFind.addAction(actionInventory);
        	repository.save(inventoryFind);
        	
        	return "update is successfuly";
        	}
        else
       		throw new IllegalArgumentException("Quantity not available....!!! Quantity available ="+inventoryFind.getQuantity());
        	  	
    }
    

    
    public List<InventoryResponse> getInventoriesByProductId(String productId){
    	
   
        return repository.findByProductId(productId).stream().map(inventory ->inventory.mapTonventoryResponse(getDetailsProduct(inventory.getDetails()))).toList();
    }
 
    
    private List<DetailsProduct> getDetailsProduct(List<Details> values) {
    	return values.stream().map(value-> productService.getDetails(value.getValueId())).toList();
    	
    }


	public String addQuentityToProduct(Long id,Integer quentity) {

		log.info("===================" +id);
		return  updateStoreItem(id,quentity,"store");
	}
    
    
}

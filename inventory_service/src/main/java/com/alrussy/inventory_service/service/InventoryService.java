package com.alrussy.inventory_service.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alrussy.inventory_service.dto.InventoryOrderRequest;
import com.alrussy.inventory_service.dto.InventoryResponse;
import com.alrussy.inventory_service.dto.LineProduct;
import com.alrussy.inventory_service.model.ActionInventory;
import com.alrussy.inventory_service.model.Inventory;
import com.alrussy.inventory_service.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

	private final InventoryRepository repository;

	public List<InventoryResponse> findAll() {
		return repository.findAll().stream()
				.map(inventory -> inventory.mapTonventoryResponseOutActions()).toList();
	}
	
	public String order(InventoryOrderRequest order) {
	List<Inventory> listInventory=new ArrayList<>();
	order.getLineProducts().forEach(lp -> {
			Inventory inventoryFind = repository.findBySkuCode(lp.getSkuCode()).orElseThrow();
			int quentityNew = inventoryFind.getQuantity();
			quentityNew = quentityNew - lp.getQuentity();

			if (quentityNew < 0) {
				throw new IllegalArgumentException("quentity not Avilable");
			}

			else {
				inventoryFind.setQuantity(quentityNew);
				
				ActionInventory actionInventory = ActionInventory.builder().dateAction(LocalDateTime.now()).numberAction(order.getOrderId().toString())
						.actionType("order").quentity(lp.getQuentity()).build();
				
				inventoryFind.addAction(actionInventory);
			listInventory.add(inventoryFind);

			}
		});
		repository.saveAll(listInventory);
		return "order is successfuly";
	}

	
	@Transactional
	public String store(List<LineProduct> lineProducts) {

		String numberAction = UUID.randomUUID().toString();
		lineProducts.stream().forEach(lp -> {
			Optional<Inventory> inventoryFind = repository.findBySkuCode(lp.getSkuCode());
			ActionInventory actionInventory = ActionInventory.builder().dateAction(LocalDateTime.now()).numberAction(numberAction)
					.actionType("store").quentity(lp.getQuentity()).build();
			
			
			if (inventoryFind.isPresent()) {
				log.info("action action={}",actionInventory.toString());
				Inventory inventory = inventoryFind.get();
				int quentityNew = inventory.getQuantity();
				quentityNew = quentityNew + lp.getQuentity();
				inventory.setQuantity(quentityNew);
				inventory.addAction(actionInventory);
				
				
				repository.save(inventory);

			}

			else {
				log.info("action num={}",actionInventory.getNumberAction());
				Inventory newInventory = Inventory.builder().skuCode(lp.getSkuCode()).quantity(lp.getQuentity()).build();
				newInventory.addAction(actionInventory);

				repository.save(newInventory);

			}

		});

		return "update is successfuly";

	}

	public String storeTest() {

		return store(List.of(
				LineProduct.builder().skuCode("123411").quentity(11).build(),
				LineProduct.builder().skuCode("123412").quentity(12).build(),
				LineProduct.builder().skuCode("123413").quentity(13).build()

		));

	}

	public String order() {
		return order(

				InventoryOrderRequest.builder().orderId("1200")
						.lineProducts(List.of(LineProduct.builder().skuCode("123411").quentity(2).build(),
								LineProduct.builder().skuCode("123412").quentity(10).build()))
						.build());
	}

	public InventoryResponse getInventoriesBySkuCode(String skuCode){
    	
        return repository.findById(skuCode).map(Inventory::mapTonventoryResponse).get();
    }

}

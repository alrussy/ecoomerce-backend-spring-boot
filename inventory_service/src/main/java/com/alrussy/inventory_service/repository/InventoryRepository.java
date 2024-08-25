
package com.alrussy.inventory_service.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.inventory_service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory,String> {




	boolean existsBySkuCode(String skuCode);

	Optional<Inventory> findBySkuCode(String skuCode);
    
}

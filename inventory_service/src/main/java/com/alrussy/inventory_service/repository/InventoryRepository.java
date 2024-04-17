
package com.alrussy.inventory_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alrussy.inventory_service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {




	boolean existsByProductId(String p);

	List<Inventory> findByProductId(String p);
    
}

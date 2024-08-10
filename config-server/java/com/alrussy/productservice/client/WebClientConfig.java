package com.alrussy.productservice.client.Inventory.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.alrussy.productservice.client.Inventory.InventoryClient;

@Configuration
public class WebClientConfig {

	@Autowired
	private LoadBalancedExchangeFilterFunction balancedExchangeFilterFunction;

	@Bean
	WebClient client(){
		return WebClient.builder().baseUrl("http://INVENTORY-SERVICE").filter(balancedExchangeFilterFunction).build();
	}
	
	@Bean
	InventoryClient inventoryClient() {
		HttpServiceProxyFactory factory= HttpServiceProxyFactory.builderFor(WebClientAdapter.create(client())).build();
		
		return factory.createClient(InventoryClient.class);
	}
}

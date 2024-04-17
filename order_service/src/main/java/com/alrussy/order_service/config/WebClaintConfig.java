package com.alrussy.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClaintConfig {

	@Bean
	WebClient webClient() {
		return WebClient.builder().baseUrl("http://localhost:9192").build();

	}

//	@Bean
//	InventoryCliant inventoryCliant() {
//		HttpServiceProxyFactory httpServiceProxyFactory=HttpServiceProxyFactory
//        .builderFor(WebClientAdapter.create(webClient())).build();
//		return httpServiceProxyFactory.createClient(InventoryCliant.class);
//	}

}

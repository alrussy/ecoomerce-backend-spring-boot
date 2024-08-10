package com.alrussy.gatewayapi.filter;

import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class ProductServiceRoute {


	@Bean
	RouterFunction<ServerResponse> ProductServiceRouteWithAdminAndUserRole(GlobalFilter filter) {
		
		return GatewayRouterFunctions.route("product-service").filter(LoadBalancerFilterFunctions.lb("product-service"))
				.route(RequestPredicates.path("/api/products/**").and(RequestPredicates.method(HttpMethod.GET)), http("http://localhost:9191"))
				.filter(filter.authorizitonFilter("USER,ADMIN"))
				.build();
	}
	
	@Bean
	RouterFunction<ServerResponse> productServiceRouteWithAdminRole(GlobalFilter filter) {
		
		return GatewayRouterFunctions.route("product-service").filter(LoadBalancerFilterFunctions.lb("product-service"))
				.route(RequestPredicates.path("/api/products/**")
						.and(RequestPredicates.methods(HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE)), http("http://localhost:9191"))
				.filter(filter.authorizitonFilter("ADMIN"))

				.build();
	}

}

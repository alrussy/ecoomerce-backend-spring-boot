package com.alrussy.gatewayapi.filter;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions;
import org.springframework.cloud.gateway.server.mvc.filter.TokenRelayFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.HandlerFunction;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import com.alrussy.gatewayapi.client.IdantityClient;
import com.google.common.net.HttpHeaders;

import lombok.extern.slf4j.Slf4j;


@Configuration
@Slf4j
public class Routes {

	@Autowired
	private IdantityClient client;
	@Autowired
	RouteValid routeValid;
	public final List<String> openApiEndpoints=List.of("/api/auth/register","/api/auth/token");

	@Bean 
	RouterFunction<ServerResponse> productServiceRoute(){
		return GatewayRouterFunctions.route("product-service").route(RequestPredicates.path("/api/products/**"),HandlerFunctions.http("http://localhost:9191")).filter(filterFunction()).build();
	}
	@Bean 
	RouterFunction<ServerResponse> orderServiceRoute(){
		return GatewayRouterFunctions.route("order_service").route(RequestPredicates.path("/api/orders/**") ,HandlerFunctions.http("http://localhost:9193")).build();
	}
	
	@Bean 
	RouterFunction<ServerResponse> inventoryServiceRoute(){
		return GatewayRouterFunctions.route("inventory_service").route(RequestPredicates.path("/api/inventory/**") ,HandlerFunctions.http("http://localhost:9192")).filter(filterFunction()).build();
	}
	
	@Bean 
	RouterFunction<ServerResponse> idantityServiceRoute(){
		return GatewayRouterFunctions.route("idantity-service").route(RequestPredicates.path("/api/auth/**") ,HandlerFunctions.http("http://localhost:9195")).filter(filterFunction()).build();
	}
	
	
	HandlerFilterFunction<ServerResponse, ServerResponse> filterFunction (){
		return (request, next) ->  
		{
			if(openApiEndpoints.contains(request.path()))
		{
				return next.handle(request);
			
		}
		if(! request.headers().asHttpHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
			throw new IllegalArgumentException("missing auth information"+ request.path());
		}
		String authHeader=request.headers().asHttpHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
		String token =authHeader.substring(7);
		log.info("======================"+client.validToken(token).getBody());
		if(client.validToken(token).getBody()) {
		return next.handle(request);
		}
		else
			throw new IllegalArgumentException("token is not valid");
	};
	}
	
	
	
}

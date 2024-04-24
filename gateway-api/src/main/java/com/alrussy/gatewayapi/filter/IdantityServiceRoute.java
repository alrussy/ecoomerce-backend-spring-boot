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
public class IdantityServiceRoute {

	@Bean
	RouterFunction<ServerResponse> IdantityServiceRouteGlobal(GlobalFilter filter) {
		
		return GatewayRouterFunctions.route("idantity-service").filter(LoadBalancerFilterFunctions.lb("idantity-service"))
				.route(RequestPredicates.path("/api/auth/**"), http("http://localhost:9195"))
				.filter(filter.authorizitonFilter("ADMIN"))
				.build();
	}
	
}

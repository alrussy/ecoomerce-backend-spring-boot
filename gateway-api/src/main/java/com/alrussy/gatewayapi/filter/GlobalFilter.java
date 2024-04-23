package com.alrussy.gatewayapi.filter;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.CharSequenceUtils;
import org.apache.commons.io.input.CharSequenceInputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.codec.CharSequenceEncoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.RequestPredicate;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.alrussy.gatewayapi.client.IdantityClient;
import com.alrussy.gatewayapi.model.UserDetails;

import brave.internal.codec.CharSequences;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.addRequestHeader;

@Slf4j
@Component
public class GlobalFilter {

	@Autowired
	private IdantityClient client;

	public static final List<String> openApiEndpoints = List.of("/api/auth/register", "/api/auth/token");
	
	public  HandlerFilterFunction<ServerResponse, ServerResponse> authorizitonFilter(String roles) {
		return (request, next) -> {
			log.info("=======================start=======================");

			String path = request.path();

			if (openApiEndpoints.contains(path)) {
				return next.handle(request);
			}
			if (!request.headers().asHttpHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				throw new IllegalArgumentException("missing auth information" + path);
			}
			String authHeader = request.headers().asHttpHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String token = authHeader.substring(7);
			var response = client.validToken(token);

			log.info(response.getBody().getRoles().get(0));
			log.info("==============================================");


			if (response.getBody() != null) {
				if (response.getBody().getRoles().stream().anyMatch(role -> roles.contains(role))) {
					ServerRequest serverRequest = ServerRequest.from(request)
							.header("X-User-Details", response.getBody().getUsername()).build();
					return next.handle(serverRequest);
				} else
					throw new IllegalArgumentException(
							"The User " + response.getBody().getUsername() + " No Authorize ");
			} else
				throw new IllegalArgumentException("token is not valid");
		};
	}

}
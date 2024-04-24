package com.alrussy.gatewayapi.filter;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.function.HandlerFilterFunction;
import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import com.alrussy.gatewayapi.utils.JwtUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class GlobalFilter {


	public static final List<String> openApiEndpoints = List.of("/api/auth/register", "/api/auth/token","/api/auth/activate-account");
	
	public  HandlerFilterFunction<ServerResponse, ServerResponse> authorizitonFilter(String roles) {
		return (request, next) -> {

			String path = request.path();
			log.info("=======================start======================="+openApiEndpoints.contains(path));

			if (openApiEndpoints.contains(path)) {
				return next.handle(request);
			}
			
			
			if (!request.headers().asHttpHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				throw new IllegalArgumentException("missing auth information" + path);
			}
			String authHeader = request.headers().asHttpHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			String token = authHeader.substring(7);
			//var response = client.validToken(token);

			if(JwtUtils.isValid(token)) {
				 var auth= JwtUtils.extractAuthoriteis(token);
				 var username=JwtUtils.extractUserName(token);

				if (auth.stream().anyMatch(role -> roles.contains(role.toString()))) {
					ServerRequest serverRequest = ServerRequest.from(request)
							.header("X-User-Details", username).build();
					return next.handle(serverRequest);
				} else
					throw new IllegalArgumentException(
							"The User " + username + " No Authorize ");
			} else
				throw new IllegalArgumentException("token is not valid");
		};
	}

}

package com.alrussy.gatewayapi.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.alrussy.gatewayapi.model.UserDetails;

@FeignClient(name="idantity-service",path = "/api/auth")
public interface IdantityClient {
	
	@PostMapping(path = "/valid")
	public ResponseEntity<UserDetails> validToken(@RequestBody String token);

}

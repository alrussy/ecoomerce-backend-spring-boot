package com.alrussy.gatewayapi.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetails {

	private String username;
	private List<String> roles;
}

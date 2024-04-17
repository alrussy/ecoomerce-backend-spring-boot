package com.alrussy.idantityservice.dto;

import com.alrussy.idantityservice.entity.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterationRequest {

	@NotNull
	@NotBlank
	@NotEmpty
	private String firstName;	
	private String lastName;
	@NotNull
	@NotBlank
	@NotEmpty
	private String email;
	@NotNull
	@NotBlank
	@NotEmpty
	private String password;
	private String confirmPassword;
	
	
	public User mapToUser() {
		return User.builder().firstName(firstName).lastName(lastName).email(email).password(password).build();
	}
	
}

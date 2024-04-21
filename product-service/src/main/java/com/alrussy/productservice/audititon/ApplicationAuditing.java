package com.alrussy.productservice.audititon;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

import jakarta.servlet.http.HttpServletRequest;


public class ApplicationAuditing implements AuditorAware<String> {

	@Autowired
	HttpServletRequest httpServletRequest;
	
    @Override
    public Optional<String> getCurrentAuditor() {
    	String userDetails=httpServletRequest.getHeader("X-User-Details").toString();
        return Optional.ofNullable(userDetails);
    }

}

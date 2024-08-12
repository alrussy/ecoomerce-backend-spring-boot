package com.alrussy.productservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.alrussy.productservice.audititon.ApplicationAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class AuditingConfig {

	 @Bean
	    AuditorAware<String> auditorAware(){

	        return new ApplicationAuditing();
	    }
}

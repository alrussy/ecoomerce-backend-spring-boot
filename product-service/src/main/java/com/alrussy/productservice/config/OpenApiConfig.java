package com.alrussy.productservice.config;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@OpenAPIDefinition
public class OpenApiConfig {
	private static final String appDescription= "api product_service";
	
	private static final String appversion= "1.0";
	
	
	@Bean
	OpenAPI openAPI() {

		return new OpenAPI()
				.info(new Info().title("Alrussy Ecommerce").version(appversion).description(appDescription)
						.termsOfService("http://www.swagger.io/terms")
						.license(new License().name("Apache 2.0").url("http://www.apahce.org/license/LICENSE-2.0")
								.extensions(Collections.emptyMap()))
						.contact(new Contact().email("alrussywe1@gmail.com").name("AlrussyDev")
								.url("https://alrussy-dev.com").extensions(Collections.emptyMap()))
//				.addSecurityItem(new SecurityRequirement().addList("Jwt", Arrays.asList("read", "write")))
//				.components(new Components().addSecuritySchemes("jwt", new SecurityScheme().name("Jwt")
//						.type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT"))
				);
	}

}

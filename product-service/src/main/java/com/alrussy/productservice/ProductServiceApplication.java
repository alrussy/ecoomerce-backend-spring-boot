package com.alrussy.productservice;

import org.hibernate.annotations.Bag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.alrussy.productservice.dto.brand_dto.BrandRequest;
import com.alrussy.productservice.dto.details_dto.DetailsRequest;
import com.alrussy.productservice.dto.details_name_dto.DetailsNameRequest;
import com.alrussy.productservice.entity.Brand;
import com.alrussy.productservice.entity.DetailsName;
import com.alrussy.productservice.entity.id.BrandCategoryId;
import com.alrussy.productservice.entity.id.DetailsId;
import com.alrussy.productservice.service.BrandService;
import com.alrussy.productservice.service.CategoryService;
import com.alrussy.productservice.service.DetailsNameService;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(BrandService brandService,DetailsNameService detailsNameService ) {
		
		
		return  args->{
			if(brandService.count()==0) {
				brandService.save(BrandRequest.builder().name("Iphone").build());
				brandService.save(BrandRequest.builder().name("Iphone").build());
			}
			if(detailsNameService.count()==0) {
				detailsNameService.save(DetailsNameRequest.builder().name("color").build());
				detailsNameService.save(DetailsNameRequest.builder().name("size").build());

	
			}
			
			
			
			
			
		};
	}
}

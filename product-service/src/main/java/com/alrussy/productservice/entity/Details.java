package com.alrussy.productservice.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.dto.details_dto.DetailsResponse;
import com.alrussy.productservice.entity.id.DetailsId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "details")
@EntityListeners(AuditingEntityListener.class)
public class Details extends Audition {
	
	@EmbeddedId
	private DetailsId id;
	
	
	private String value;
	
	@ManyToOne
	@MapsId("detailsNameId")
	private DetailsName detailsName;
	
	
	public DetailsResponse mapToDetailsValueResponse(){
		return DetailsResponse.builder()
				.id(id.getDetailsId())
				.value(value)
				.name(detailsName.getDetailsName())
				.build(); 
	}
	

}

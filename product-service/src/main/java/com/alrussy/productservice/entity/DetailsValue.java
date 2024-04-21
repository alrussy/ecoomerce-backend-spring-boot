package com.alrussy.productservice.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.dto.details_value_dto.DetailsValueResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "detailsValue")
@EntityListeners(AuditingEntityListener.class)
public class DetailsValue extends Audition {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String detailsValue;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "detailsNameId")
	private DetailsName detailsName;
	
	
	public DetailsValueResponse mapToDetailsValueResponse(){
		return DetailsValueResponse.builder()
				.id(id)
				.value(detailsValue)
				.name(detailsName.getDetailsName())
				.build(); 
	}
	

}

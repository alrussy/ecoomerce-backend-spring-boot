package com.alrussy.productservice.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.audititon.Audition;
import com.alrussy.productservice.dto.DepartmentResponse;
import com.alrussy.productservice.entity.id.DeparmentId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
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
@Table(name = "departments")
@EntityListeners(AuditingEntityListener.class)
public class Department extends Audition{
	
	@EmbeddedId
	private DeparmentId id;
	private String name;
	@ManyToOne
	@MapsId("categoryId")
	private Category category;

	public DepartmentResponse mapToDepartmentResponse() {
		return new DepartmentResponse(id.getDepartmentId(), name);
		
	}
}

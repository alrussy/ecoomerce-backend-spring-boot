package com.alrussy.productservice.entity;

import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.alrussy.productservice.dto.department_dto.DepartmentResponse;
import com.alrussy.productservice.entity.id.DepartmentId;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
	private DepartmentId id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="category_id",insertable = false,updatable = false)
	@MapsId("categoryId")
	private Category category;

	public DepartmentResponse mapToDepartmentResponseOutCategory() {
		return new DepartmentResponse(id.getDepartmentId(), name);
		
	}
}

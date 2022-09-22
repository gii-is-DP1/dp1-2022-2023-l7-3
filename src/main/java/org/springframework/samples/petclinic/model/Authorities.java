package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "authorities")
public class Authorities extends BaseEntity{
	
	@ManyToOne
	@JoinColumn(name = "username")
	User user;
	
	@Size(min = 3, max = 50)
	String authority;
	
	
}

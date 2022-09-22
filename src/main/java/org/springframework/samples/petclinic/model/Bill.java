package org.springframework.samples.petclinic.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RevisionEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Audited
@Entity
public class Bill extends BaseEntity{		
	
	@OneToOne
	@NotAudited
	Visit visit;
	
	double amount;
	
	String concept;
}

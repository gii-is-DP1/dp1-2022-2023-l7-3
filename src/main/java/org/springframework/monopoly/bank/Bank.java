package org.springframework.monopoly.bank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "bank")
public class Bank {
	
	@Column(name = "numProperties")
	@NotEmpty
	private Integer numProperties;

	public Integer getNumProperties() {
		return numProperties;
	}

	public void setNumProperties(Integer numProperties) {
		this.numProperties = numProperties;
	}
	

}

package org.springframework.samples.petclinic.projections;

import java.time.LocalDate;

public interface PetVisit {
	String getName();
	String getTypeName();
	LocalDate getDate();
	String getDescription();

}

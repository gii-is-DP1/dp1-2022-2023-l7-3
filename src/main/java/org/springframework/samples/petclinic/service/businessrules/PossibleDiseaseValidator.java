package org.springframework.samples.petclinic.service.businessrules;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.samples.petclinic.model.Diagnose;
import org.springframework.samples.petclinic.model.Disease;
import org.springframework.samples.petclinic.model.PetType;

public class PossibleDiseaseValidator implements ConstraintValidator<ValidatePossibleDisease, Diagnose>{

	@Override
	public boolean isValid(Diagnose diagnose, ConstraintValidatorContext context) {
		Disease disease=diagnose.getDisease();
		PetType petType=diagnose.getVisit().getPet().getType();		
		return disease.getPetTypeswithPrevalence().contains(petType);
	}

}

package org.springframework.samples.petclinic.web;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;

import org.springframework.samples.petclinic.model.Diagnose;
import org.springframework.samples.petclinic.model.PetType;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DiagnoseValidator implements Validator {

	String IMPOSSIBLE_DISEASE="ImpossibleDisease";
		
	
	@Override
	public void validate(Object obj, Errors errors) {
		Diagnose diagnose = (Diagnose) obj;
		if(diagnose.getVisit()!=null) {
			PetType petType=diagnose.getVisit().getPet().getType();
		
			if(!diagnose.getDisease().getPetTypeswithPrevalence().contains(petType)) {
				errors.rejectValue("disease",IMPOSSIBLE_DISEASE,"A pet of type "+petType+" cannot develop "+diagnose.getDisease().getName());
			}						
		}
	}

	/**
	 * This Validator validates *just* Diagnose instances
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return Diagnose.class.isAssignableFrom(clazz);
	}

	
}

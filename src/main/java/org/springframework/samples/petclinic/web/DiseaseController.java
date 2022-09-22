package org.springframework.samples.petclinic.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Disease;
import org.springframework.samples.petclinic.service.DiseaseService;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/diseases")
public class DiseaseController {

	public static final String DISEASES_FORM="diseases/createOrUpdateDiseaseForm";
	public static final String DISEASES_LISTING="diseases/DiseasesListing";
	
	@Autowired
	DiseaseService diseasesService;
	
	@Autowired
	PetService petService;
	
	@GetMapping
	public String listDiseases(ModelMap model)
	{
		model.addAttribute("diseases",diseasesService.findAll());
		return DISEASES_LISTING;
	}
	
	@GetMapping("/{id}/edit")
	public String editDisease(@PathVariable("id") int id,ModelMap model) {
		Optional<Disease> disease=diseasesService.findById(id);
		if(disease.isPresent()) {
			model.addAttribute("disease",disease.get());
			model.addAttribute("petTypes",petService.findPetTypes());
			return DISEASES_FORM;
		}else {
			model.addAttribute("message","We cannot find the disease you tried to edit!");
			return listDiseases(model);
		}
	}
	
	@PostMapping("/{id}/edit")
	public String editDisease(@PathVariable("id") int id, @Valid Disease modifiedDisease, BindingResult binding, ModelMap model) {
		Optional<Disease> disease=diseasesService.findById(id);
		if(binding.hasErrors()) {			
			return DISEASES_FORM;
		}else {
			BeanUtils.copyProperties(modifiedDisease, disease.get(), "id");
			diseasesService.save(disease.get());
			model.addAttribute("message","Disease updated succesfully!");
			return listDiseases(model);
		}
	}
	
	@GetMapping("/{id}/delete")
	public String deleteDisease(@PathVariable("id") int id,ModelMap model) {
		Optional<Disease> disease=diseasesService.findById(id);
		if(disease.isPresent()) {
			diseasesService.delete(disease.get());
			model.addAttribute("messageType","danger");
			model.addAttribute("message","The disease was deleted successfully!");
			return listDiseases(model);
		}else {
			model.addAttribute("messageType","danger");
			model.addAttribute("message","We cannot find the disease you tried to delete!");
			return listDiseases(model);
		}
	}
	
	@GetMapping("/new")
	public String editNewDisease(ModelMap model) {
		model.addAttribute("disease",new Disease());
		model.addAttribute("petTypes",petService.findPetTypes());
		return DISEASES_FORM;
	}
	
	@PostMapping("/new")
	public String saveNewDisease(@Valid Disease disease, BindingResult binding,ModelMap model) {
		if(binding.hasErrors()) {			
			return DISEASES_FORM;
		}else {
			diseasesService.save(disease);
			model.addAttribute("message", "The disease was created successfully!");			
			return listDiseases(model);
		}
	}
}

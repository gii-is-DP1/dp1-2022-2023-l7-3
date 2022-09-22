package org.springframework.samples.petclinic.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Payment;
import org.springframework.samples.petclinic.repository.PaymentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {
	@Autowired
	PaymentRepository pr;
	
	@Transactional
	public void save(Payment p) {
		pr.save(p);
	}
	
	@Transactional(readOnly = true)
	public List<Payment> findAll(){
		return pr.findAll();
	}
}

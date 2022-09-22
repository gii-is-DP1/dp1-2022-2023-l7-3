package org.springframework.samples.petclinic.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Integer> {
	List<Payment> findAll();
}

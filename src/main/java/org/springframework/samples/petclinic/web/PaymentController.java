package org.springframework.samples.petclinic.web;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Owner;
import org.springframework.samples.petclinic.model.Payment;
import org.springframework.samples.petclinic.service.OwnerService;
import org.springframework.samples.petclinic.service.PaymentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaymentController {

	@Autowired
	PaymentService ps;
	
	@Autowired
	OwnerService os;
	
	@GetMapping("/payments/random/new")
	public @ResponseBody Payment createRandomPayment() {
		Payment payment=new Payment();
		payment.setOwner(findRandomOwner());
		payment.setAmount(Math.random()*100);
		ps.save(payment);
		return payment;
	}
	
	@GetMapping("/payments/random/modify")
	public @ResponseBody Payment modifyRandomPayment() {
		List<Payment> payments=ps.findAll();
		int index = (int) (Math.random() * payments.size());
		Payment payment=payments.get(index);
		payment.setOwner(findRandomOwner());
		payment.setAmount(Math.random()*100);
		ps.save(payment);
		return payment;
	}
	

	private Owner findRandomOwner() {
		Owner result=null;
		Collection<Owner> owners=os.findOwnerByLastName("");
		int num = (int) (Math.random() * owners.size());
	    for(Owner o: owners) { 
	    	if (--num < 0) { 
	    		result=o;
	    		break;
	    	}
	    }
	    return result;
	}
}

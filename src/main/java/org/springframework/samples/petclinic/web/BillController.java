package org.springframework.samples.petclinic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.model.Bill;
import org.springframework.samples.petclinic.model.Visit;
import org.springframework.samples.petclinic.service.BillService;
import org.springframework.samples.petclinic.service.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BillController {

	@Autowired
	BillService bs;
	
	@Autowired
	VisitService vs;
	
	@GetMapping("/bills/random/new")
	public @ResponseBody Bill createRandomBill() {
		Bill bill=new Bill();
		bill.setAmount(Math.random()*100);
		bill.setConcept("A randomConcept");
		bill.setVisit(findRandomVisit());		
		bs.save(bill);
		return bill;
	}
	
	@GetMapping("/bills/random/modify")
	public @ResponseBody Bill modifyRandomBill() {
		List<Bill> bills=bs.findAll();
		int index = (int) (Math.random() * bills.size());
		Bill bill=bills.get(index);		
		bill.setAmount(Math.random()*100);
		bill.setVisit(findRandomVisit());		
		bs.save(bill);
		return bill;
	}
	

	private Visit findRandomVisit() {
		Visit result=null;
		List<Visit> visits=vs.findAll();
		int num = (int) (Math.random() * visits.size());
	    for(Visit o: visits) { 
	    	if (--num < 0) { 
	    		result=o;
	    		break;
	    	}
	    }
	    return result;
	}

}

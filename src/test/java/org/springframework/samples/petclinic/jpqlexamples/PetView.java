package org.springframework.samples.petclinic.jpqlexamples;

import org.springframework.samples.petclinic.owner.Owner;

public interface PetView {
    public String getName();
    public Owner getOwner();
}

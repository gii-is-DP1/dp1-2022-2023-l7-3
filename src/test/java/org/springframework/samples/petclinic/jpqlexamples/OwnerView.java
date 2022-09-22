package org.springframework.samples.petclinic.jpqlexamples;

public class OwnerView {
    // public String getFirstName();
    // public String getLastName();
    private String firstName;
    private String lastName;
    
    public OwnerView(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "OwnerView [firstName=" + firstName + ", lastName=" + lastName + "]";
    }    

    
    
}

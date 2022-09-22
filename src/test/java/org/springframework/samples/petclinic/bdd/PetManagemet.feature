Feature: Pet management 
   As an owner I can manage my pets 
  
  Scenario: Successful addition of a pet (Positive)
    Given I am logged in the system as "admin1"
    When I add a new pet  
    Then the new pet appears in my profile page
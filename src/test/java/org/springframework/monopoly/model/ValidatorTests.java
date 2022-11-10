package org.springframework.monopoly.model;

/**
 * @author Michael Isvy Simple test to make sure that Bean Validation is working (useful
 * when upgrading to a new version of Hibernate Validator/ Bean Validation)
 */
class ValidatorTests {

//	private Validator createValidator() {
//		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
//		localValidatorFactoryBean.afterPropertiesSet();
//		return localValidatorFactoryBean;
//	}

//	@Test
//	void shouldNotValidateWhenFirstNameEmpty() {
//
//		LocaleContextHolder.setLocale(Locale.ENGLISH);
//		Person person = new Person();
//		person.setFirstName("");
//		person.setLastName("smith");
//
//		Validator validator = createValidator();
//		Set<ConstraintViolation<Person>> constraintViolations = validator.validate(person);
//
//		assertThat(constraintViolations.size()).isEqualTo(1);
//		ConstraintViolation<Person> violation = constraintViolations.iterator().next();
//		assertThat(violation.getPropertyPath().toString()).isEqualTo("firstName");
//		assertThat(violation.getMessage()).isEqualTo("must not be empty");
//	}

}

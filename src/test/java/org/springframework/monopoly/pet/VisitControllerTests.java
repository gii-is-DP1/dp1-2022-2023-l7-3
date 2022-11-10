package org.springframework.monopoly.pet;


/**
 * Test class for {@link VisitController}
 *
 * @author Colin But
 */
//@WebMvcTest(controllers = VisitController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class VisitControllerTests {

//	private static final int TEST_PET_ID = 1;
//
//	@Autowired
//	private VisitController visitController;
//
//	@MockBean
//	private PetService clinicService;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	void setup() {
//		given(this.clinicService.findPetById(TEST_PET_ID)).willReturn(new Pet());
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testInitNewVisitForm() throws Exception {
//		mockMvc.perform(get("/owners/*/pets/{petId}/visits/new", TEST_PET_ID)).andExpect(status().isOk())
//				.andExpect(view().name("pets/createOrUpdateVisitForm"));
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessNewVisitFormSuccess() throws Exception {
//		mockMvc.perform(post("/owners/*/pets/{petId}/visits/new", TEST_PET_ID).param("name", "George").with(csrf())
//				.param("description", "Visit Description")).andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/owners/{ownerId}"));
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testProcessNewVisitFormHasErrors() throws Exception {
//		mockMvc.perform(post("/owners/*/pets/{petId}/visits/new", TEST_PET_ID).with(csrf()).param("name", "George"))
//				.andExpect(model().attributeHasErrors("visit")).andExpect(status().isOk())
//				.andExpect(view().name("pets/createOrUpdateVisitForm"));
//	}
//
//	@WithMockUser(value = "spring")
//	@Test
//	void testShowVisits() throws Exception {
//		mockMvc.perform(get("/owners/*/pets/{petId}/visits", TEST_PET_ID)).andExpect(status().isOk())
//				.andExpect(model().attributeExists("visits")).andExpect(view().name("visitList"));
//	}

}

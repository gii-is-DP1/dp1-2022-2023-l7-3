package org.springframework.monopoly.pet;


/**
 * Test class for the {@link PetController}
 *
 * @author Colin But
 */
//@WebMvcTest(value = PetController.class,
//		includeFilters = @ComponentScan.Filter(value = PetTypeFormatter.class, type = FilterType.ASSIGNABLE_TYPE),
//		excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
//		excludeAutoConfiguration= SecurityConfiguration.class)
class PetControllerTests {

//	private static final int TEST_OWNER_ID = 1;
//
//	private static final int TEST_PET_ID = 1;
//
//
//	@MockBean
//	private PetService petService;
//        
//        @MockBean
//	private OwnerService ownerService;
//
//	@Autowired
//	private MockMvc mockMvc;
//
//	@BeforeEach
//	void setup() {
//		PetType cat = new PetType();
//		cat.setId(3);
//		cat.setName("hamster");
//		given(this.petService.findPetTypes()).willReturn(Lists.newArrayList(cat));
//		given(this.ownerService.findOwnerById(TEST_OWNER_ID)).willReturn(new Owner());
//		given(this.petService.findPetById(TEST_PET_ID)).willReturn(new Pet());
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testInitCreationForm() throws Exception {
//		mockMvc.perform(get("/owners/{ownerId}/pets/new", TEST_OWNER_ID)).andExpect(status().isOk())
//				.andExpect(view().name("pets/createOrUpdatePetForm")).andExpect(model().attributeExists("pet"));
//	}
//
//	@WithMockUser(value = "spring")
//        @Test
//	void testProcessCreationFormSuccess() throws Exception {
//		mockMvc.perform(post("/owners/{ownerId}/pets/new", TEST_OWNER_ID)
//							.with(csrf())
//							.param("name", "Betty")
//							.param("type", "hamster")
//							.param("birthDate", "2015/02/12"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/owners/{ownerId}"));
//	}
//
//	@WithMockUser(value = "spring")
//    @Test
//	void testProcessCreationFormHasErrors() throws Exception {
//		mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
//							.with(csrf())
//							.param("name", "Betty")
//							.param("birthDate", "2015/02/12"))
//				.andExpect(model().attributeHasNoErrors("owner"))
//				.andExpect(model().attributeHasErrors("pet"))
//				.andExpect(status().isOk())
//				.andExpect(view().name("pets/createOrUpdatePetForm"));
//	}
//
//    @WithMockUser(value = "spring")
//	@Test
//	void testInitUpdateForm() throws Exception {
//		mockMvc.perform(get("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID))
//				.andExpect(status().isOk()).andExpect(model().attributeExists("pet"))
//				.andExpect(view().name("pets/createOrUpdatePetForm"));
//	}
//    
//    @WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateFormSuccess() throws Exception {
//		mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
//							.with(csrf())
//							.param("name", "Betty")
//							.param("type", "hamster")
//							.param("birthDate", "2015/02/12"))
//				.andExpect(status().is3xxRedirection())
//				.andExpect(view().name("redirect:/owners/{ownerId}"));
//	}
//    
//    @WithMockUser(value = "spring")
//	@Test
//	void testProcessUpdateFormHasErrors() throws Exception {
//		mockMvc.perform(post("/owners/{ownerId}/pets/{petId}/edit", TEST_OWNER_ID, TEST_PET_ID)
//							.with(csrf())
//							.param("name", "Betty")
//							.param("birthDate", "2015/02/12"))
//				.andExpect(model().attributeHasNoErrors("owner"))
//				.andExpect(model().attributeHasErrors("pet")).andExpect(status().isOk())
//				.andExpect(view().name("pets/createOrUpdatePetForm"));
//	}

}

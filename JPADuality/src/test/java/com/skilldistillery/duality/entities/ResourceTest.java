package com.skilldistillery.duality.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ResourceTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Resource resource;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPADuality");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		resource = em.find(Resource.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		resource = null;
	}

	@Test
	void test_resource_basic() {
		assertNotNull(resource);
		assertNotNull("National Alliance on metal illness", resource.getDescription());
		assertEquals("National Alliance on metal illness", resource.getDescription());
	
	}
//	@Test
//	void test_resource_to__mapping() {
//		assertNotNull(resource);
//		
//	}

}

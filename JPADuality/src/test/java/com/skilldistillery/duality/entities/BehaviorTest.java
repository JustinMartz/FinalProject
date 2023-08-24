package com.skilldistillery.duality.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BehaviorTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Behavior behavior;

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
		behavior = em.find(Behavior.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		behavior = null;
	}

	@Test
	void test_user_basic() {
		assertNotNull(behavior);
		assertEquals("increased need for sleep", behavior.getName());
		assertEquals(1, behavior.getSeverity());
	}
	@Test
	void test_user_to_behavior_type_mapping() {
		assertNotNull(behavior);
		assertNotNull(behavior.getBehaviorType());
		assertEquals(1, behavior.getBehaviorType().getId());
		
	}

}

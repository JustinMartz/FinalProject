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

class BehaviorTypeTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private BehaviorType behaviorType;

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
		behaviorType = em.find(BehaviorType.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		behaviorType = null;
	}

	@Test
	void test_behaviorType_basic() {
		assertNotNull(behaviorType);
		assertEquals("depressive", behaviorType.getName());
		assertEquals("Continued feelings of sadness, hopelessness, pessimism, emptiness. Fatigue, lack of energy. Insomnia or other sleep issues, such as waking up very early or sleeping too much. Anxiety, irritability, restlessness.", behaviorType.getDescription());
		
		
	}

	@Test
	void test_behaviorType_to_behavior_type_mapping() {
		assertNotNull(behaviorType);

	}
}

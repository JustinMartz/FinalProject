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

class BehaviorReportTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private BehaviorReport behaviorReport;

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
		behaviorReport = em.find(BehaviorReport.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		behaviorReport = null;
	}

	@Test
	void test_user_basic() {
		assertNotNull(behaviorReport);
		assertEquals(2023, behaviorReport.getCreateDate().getYear());
		assertEquals(8, behaviorReport.getCreateDate().getMonthValue());
		assertEquals(5, behaviorReport.getIntensity());
	}
	@Test
	void test_user_to_behavior_report_mapping() {
		assertNotNull(behaviorReport);
		assertEquals(1, behaviorReport.getBehavior().getId());
		assertEquals(4, behaviorReport.getUser().getId());
		
		
		
	}

}

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

class BehaviorReportRemarkTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private BehaviorReportRemark behaviorReportRemark;

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
		behaviorReportRemark = em.find(BehaviorReportRemark.class, 1);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		behaviorReportRemark = null;
	}

	@Test
	void test_user_basic() {
		assertNotNull(behaviorReportRemark);
		assertEquals("dog ran away and girlfriend broke up with me", behaviorReportRemark.getRemarks());
		assertEquals(2023, behaviorReportRemark.getCreateDate().getYear());
		assertEquals(8, behaviorReportRemark.getCreateDate().getMonthValue());
		
	}
	@Test
	void test_user_to_behavior_report_remark_mapping() {
		assertNotNull(behaviorReportRemark);

		
	}

}

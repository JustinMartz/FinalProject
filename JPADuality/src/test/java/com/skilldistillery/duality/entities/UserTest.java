package com.skilldistillery.duality.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private User user;

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
		user = em.find(User.class, 4);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		user = null;
	}

	@Test
	void test_user_basic() {
		assertNotNull(user);
		assertEquals("user1", user.getUsername());
	}

	@Test
	void test_user_to_behaviorReports_mapping() {
		assertNotNull(user);
		assertNotNull(user.getBehaviorReports());
		assertTrue(user.getBehaviorReports().size() > 0); 
	}

	@Test
	void test_user_lists_for_size() {
		assertNotNull(user.getBehaviorReportRemarks());
		assertTrue(user.getBehaviorReportRemarks().size() > 0);
		assertTrue(user.getBehaviorReports().size() > 0);
		user = em.find(User.class, 1);
		assertTrue(user.getResources().size() > 0);
	}

	@Test
	void test_user_posts() {
		user = em.find(User.class, 5);
		assertTrue(user.getPosts().size() > 0);
		assertTrue(user.getFlaggedPosts().size() > 0);
	}
}

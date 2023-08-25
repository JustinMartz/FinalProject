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

class DirectMessageTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private DirectMessage directMessage;

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
		directMessage = em.find(DirectMessage.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		directMessage = null;
	}

	@Test
	void test_directMessage_basic() {
		assertNotNull(directMessage);
		assertEquals(2023, directMessage.getCreateDate().getYear());
		assertEquals(8, directMessage.getCreateDate().getMonthValue());
		assertEquals("Yo what's up!", directMessage.getMessage());
		
	}
	
	@Test
	void test_direct_message_get_recipient() {
		assertNotNull(directMessage);
		assertEquals(1, directMessage.getRecipient().getId());
		
		
	}
	
	@Test
	void test_direct_message_get_sender() {
		assertNotNull(directMessage);
		assertEquals(1, directMessage.getSender().getId());
		
	}

}

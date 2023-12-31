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

class CommentTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Comment comment;

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
		comment = em.find(Comment.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		comment = null;
	}

	@Test
	void test_comment_basic() {
		assertNotNull(comment);
		assertEquals(true, comment.getFlagged());
		assertEquals("you suck", comment.getBody());
		assertEquals(2023, comment.getCreateDate().getYear());
		assertEquals(8, comment.getCreateDate().getMonthValue());
		assertEquals(true, comment.getActive());
		
		
	}
	@Test
	void test_comment_to_other_class_relationship() {
		assertNotNull(comment);
		assertNotNull(comment.getCommentor().getId());
		assertNotNull(comment.getPost().getId());
		
	}

}

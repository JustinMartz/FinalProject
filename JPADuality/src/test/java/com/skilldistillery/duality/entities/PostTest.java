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

class PostTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Post post;

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
		post = em.find(Post.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		post = null;
	}

	@Test
	void test_post_basic() {
		assertNotNull(post);
		assertEquals("how are ya'll feeling today", post.getTitle());
		assertEquals(2023, post.getCreateDate().getYear());
		assertEquals(8, post.getCreateDate().getMonthValue());
		assertEquals("feeling good today", post.getMessage());
		assertTrue(post.getAnonymous());
		assertTrue(post.getActive());
		assertTrue(post.getPersonal());
		
		
		
	}
	@Test
	void test_post_to_comment() {
		assertNotNull(post.getComments());
		assertNotNull(post.getCreator());
		assertTrue(post.getComments().size()>0);
		assertEquals("how are ya'll feeling today", post.getTitle());
		
	}
	
	@Test
	void test_post_to_user() {
		post = em.find(Post.class, 2);
		assertNotNull(post);
		assertTrue(post.getUsersWhoFlagged().size()>0);
	}

}

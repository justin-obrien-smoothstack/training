package com.ss.training.lms.versionone.test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ss.training.lms.versionone.entities.Author;

/**
 * @author Justin O'Brien
 *
 */
public class AuthorTest { // all passed
	static Author author;
	static Author equalAuthorA = new Author(2, "AuthorA");
	static Author equalAuthorB = new Author(2, "AuthorB");
	static Author unequalAuthorA = new Author(1, "Author");
	static Author unequalAuthorB = new Author(2, "Author");

	@Before
	public void before() {
		author = new Author(1, "Author");
	}

	@Test
	public void constructorTest() {
		Author newAuthor = new Author(4, "New Author");
		assertEquals(newAuthor.getId(), 4);
		assertEquals(newAuthor.getName(), "New Author");
	}

	@Test
	public void getIdTest() {
		assertEquals(author.getId(), 1);
	}

	@Test
	public void getNameTest() {
		assertEquals(author.getName(), "Author");
	}

	@Test
	public void setNameTest() {
		author.setName("Name");
		assertEquals(author.getName(), "Name");
	}

	@Test
	public void hashCodePosTest() {
		assertNotEquals(unequalAuthorA.hashCode(), unequalAuthorB.hashCode());
	}

	@Test
	public void hashCodeNegTest() {
		assertEquals(equalAuthorA.hashCode(), equalAuthorB.hashCode());
	}

	@Test
	public void equalsPosTest() {
		assertFalse(unequalAuthorA.equals(unequalAuthorB));
	}

	@Test
	public void equalsNegTest() {
		assertTrue(equalAuthorA.equals(equalAuthorB));
	}
}

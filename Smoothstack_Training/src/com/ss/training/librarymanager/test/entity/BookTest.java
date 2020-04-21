package com.ss.training.librarymanager.test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ss.training.librarymanager.entities.Book;

/**
 * @author Justin O'Brien
 *
 */
public class BookTest { // all passed

	static Book book;
	static Book equalBookA = new Book(2, "BookA", 1, 3);
	static Book equalBookB = new Book(2, "BookB", 2, 4);
	static Book unequalBookA = new Book(1, "Book", 1, 1);
	static Book unequalBookB = new Book(2, "Book", 1, 1);
	
	@Before
	public void before() {
		book = new Book(1, "Book", 2, 3);
	}

	@Test
	public void constructorTest() {
		Book newBook = new Book(4, "New Book", 5, 6);
		assertEquals(newBook.getId(), 4);
		assertEquals(newBook.getTitle(), "New Book");
		assertEquals(newBook.getAuthor(), 5);
		assertEquals(newBook.getPublisher(), 6);
	}

	@Test
	public void getIdTest() {
		assertEquals(book.getId(), 1);
	}

	@Test
	public void getTitleTest() {
		assertEquals(book.getTitle(), "Book");
	}

	@Test
	public void setTitleTest() {
		book.setTitle("Title");
		assertEquals(book.getTitle(), "Title");
	}

	@Test
	public void getAuthorTest() {
		assertEquals(book.getAuthor(), 2);
	}

	@Test
	public void setAuthorTest() {
		book.setAuthor(7);
		assertEquals(book.getAuthor(), 7);
	}

	@Test
	public void getPublisherTest() {
		assertEquals(book.getPublisher(), 3);
	}

	@Test
	public void setPublisherTest() {
		book.setPublisher(8);
		assertEquals(book.getPublisher(), 8);
	}

	@Test
	public void hashCodePosTest() {
		assertNotEquals(unequalBookA.hashCode(), unequalBookB.hashCode());
	}

	@Test
	public void hashCodeNegTest() {
		assertEquals(equalBookA.hashCode(), equalBookB.hashCode());
	}

	@Test
	public void equalsPosTest() {
		assertFalse(unequalBookA.equals(unequalBookB));
	}

	@Test
	public void equalsNegTest() {
		assertTrue(equalBookA.equals(equalBookB));
	}
}

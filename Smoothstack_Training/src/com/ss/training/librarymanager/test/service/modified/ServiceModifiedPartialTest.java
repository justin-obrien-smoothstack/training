package com.ss.training.librarymanager.test.service.modified;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;
import com.ss.training.librarymanager.services.AuthorService;
import com.ss.training.librarymanager.services.BookService;
import com.ss.training.librarymanager.services.PublisherService;

/**
 * @author Justin O'Brien
 *
 */
public class ServiceModifiedPartialTest { // all passed except BookServiceGetModifiedNegTest

	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Scanner scanner;
	static BookService bookService;
	static AuthorService authorService;
	static PublisherService publisherService;

	@Before
	public void before() {
		bookService = BookService.getInstance(books, authors, publishers, scanner);
		System.out.println(bookService.getModified());
		authorService = AuthorService.getInstance(books, authors, publishers, scanner);
		publisherService = PublisherService.getInstance(books, authors, publishers, scanner);
	}

	@Test
	public void BookServiceSetModifiedPosTest() {
		bookService.setModified(false);
		bookService.setModified(true);
		assertTrue(bookService.getModified());
	}

	@Test
	public void BookServiceSetModifiedNegTest() {
		bookService.setModified(true);
		bookService.setModified(false);
		assertFalse(bookService.getModified());
	}

	public void BookServiceGetModifiedPosTest() {

	}

	@Test
	public void BookServiceGetModifiedNegTest() {
		assertFalse(bookService.getModified());
	}

	public void AuthorServiceGetModifiedPosTest() {

	}

	@Test
	public void AuthorServiceGetModifiedNegTest() {
		assertFalse(authorService.getModified());
	}

	public void PublisherServiceGetModifiedPosTest() {

	}

	@Test
	public void PublisherServiceGetModifiedNegTest() {
		assertFalse(publisherService.getModified());
	}

}

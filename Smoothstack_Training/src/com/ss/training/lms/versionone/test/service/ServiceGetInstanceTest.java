package com.ss.training.lms.versionone.test.service;

import static org.junit.Assert.assertSame;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

import com.ss.training.lms.versionone.entities.Author;
import com.ss.training.lms.versionone.entities.Book;
import com.ss.training.lms.versionone.entities.Publisher;
import com.ss.training.lms.versionone.services.AuthorService;
import com.ss.training.lms.versionone.services.BookService;
import com.ss.training.lms.versionone.services.PublisherService;

/**
 * @author Justin O'Brien
 *
 */
public class ServiceGetInstanceTest { // all passed
	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Book bookOne = new Book(1, "Book1", 1, 1);
	static Author authorOne = new Author(1, "Author1");
	static Publisher publisherOne = new Publisher(1, "Publisher1", "Address1");
	static Scanner scanner;
	static BookService bookServiceOne, bookServiceTwo;
	static AuthorService authorServiceOne, authorServiceTwo;
	static PublisherService publisherServiceOne, publisherServiceTwo;

	@Test
	public void bookServiceTest() {
		bookServiceOne = BookService.getInstance(books, authors, publishers, scanner);
		bookServiceTwo = BookService.getInstance(books, authors, publishers, scanner);
		assertSame(bookServiceOne, bookServiceTwo);
	}

	@Test
	public void authorServiceTest() {
		authorServiceOne = AuthorService.getInstance(books, authors, publishers, scanner);
		authorServiceTwo = AuthorService.getInstance(books, authors, publishers, scanner);
		assertSame(authorServiceOne, authorServiceTwo);
	}

	@Test
	public void publisherServiceTest() {
		publisherServiceOne = PublisherService.getInstance(books, authors, publishers, scanner);
		publisherServiceTwo = PublisherService.getInstance(books, authors, publishers, scanner);
		assertSame(publisherServiceOne, publisherServiceTwo);
	}
}
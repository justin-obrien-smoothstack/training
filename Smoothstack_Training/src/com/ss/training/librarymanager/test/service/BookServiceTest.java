package com.ss.training.librarymanager.test.service;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

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

public class BookServiceTest {

	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Book bookOne, bookTwo;
	static Author authorOne, authorTwo;
	static Publisher publisherOne, publisherTwo;
	static Scanner scanner;
	static BookService bookService;
	static AuthorService authorService;
	static PublisherService publisherService;

	@Rule
	public final TextFromStandardInputStream stdIn = TextFromStandardInputStream.emptyStandardInputStream();

	@Before
	public void before() {
		books = new HashMap<Long, Book>();
		authors = new HashMap<Long, Author>();
		publishers = new HashMap<Long, Publisher>();
		bookOne = new Book(1, "Book1", 1, 1);
		authorOne = new Author(1, "Author1");
		publisherOne = new Publisher(1, "Publisher1", "Address1");
		books.put(bookOne.getId(), bookOne);
		authors.put(authorOne.getId(), authorOne);
		publishers.put(publisherOne.getId(), publisherOne);
	}

	@Test
	public void getInstancePosTest() {

	}

	@Test
	public void getInstanceNegTest() {

	}

	@Test
	public void getModifiedPosTest() {

	}

	@Test
	public void getModifiedNegTest() {

	}

	@Test
	public void setModifiedPosTest() {

	}

	@Test
	public void setModifiedNegTest() {

	}

	@Test
	public void createPosTest() {
		try (Scanner scanner = new Scanner(System.in)) {
			stdIn.provideLines("Book2", "1", "1");
			BookService.getInstance(books, authors, publishers, scanner).create();
			Book bookTwo = books.get((long) 2);
			assertTrue("Book2".equals(bookTwo.getTitle()) && bookTwo.getAuthor() == 1 && bookTwo.getPublisher() == 1);
		}
	}

	@Test
	public void createNegTest() {
		try (Scanner scanner = new Scanner(System.in)) {

		}
	}

	@Test
	public void readPosTest() {
		try (Scanner scanner = new Scanner(System.in)) {

		}
	}

	@Test
	public void readNegTest() {
		try (Scanner scanner = new Scanner(System.in)) {

		}
	}

	@Test
	public void updatePosTest() {
		try (Scanner scanner = new Scanner(System.in)) {

		}
	}

	@Test
	public void updateNegTest() {
		try (Scanner scanner = new Scanner(System.in)) {

		}
	}

	@Test
	public void deletePosTest() {
		try (Scanner scanner = new Scanner(System.in)) {

		}
	}

	@Test
	public void deleteNegTest() {
		try (Scanner scanner = new Scanner(System.in)) {

		}
	}

}

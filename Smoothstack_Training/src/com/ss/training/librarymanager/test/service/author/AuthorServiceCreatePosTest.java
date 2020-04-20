package com.ss.training.librarymanager.test.service.author;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;
import com.ss.training.librarymanager.services.AuthorService;

/**
 * @author Justin O'Brien
 *
 */
public class AuthorServiceCreatePosTest {
	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Book bookOne, bookTwo;
	static Author authorOne, authorTwo;
	static Publisher publisherOne, publisherTwo;
	static Scanner scanner;

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
		scanner = new Scanner(System.in);
	}

	@Test
	public void test() { // passed
		stdIn.provideLines("Author2");
		AuthorService.getInstance(books, authors, publishers, scanner).create();
		authorTwo = authors.get((long) 2);
		assertEquals("Author2", authorTwo.getName());
	}
}

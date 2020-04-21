package com.ss.training.librarymanager.test.service.modified;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;
import com.ss.training.librarymanager.services.BookService;

/**
 * @author Justin O'Brien
 *
 */
public class BookServiceGetModifiedPosTest { // passed

	@Rule
	public final TextFromStandardInputStream stdIn = TextFromStandardInputStream.emptyStandardInputStream();

	static HashMap<Long, Book> books = new HashMap<Long, Book>();
	static HashMap<Long, Author> authors = new HashMap<Long, Author>();
	static HashMap<Long, Publisher> publishers = new HashMap<Long, Publisher>();
	static Book bookOne = new Book(1, "Book1", 1, 1);
	static Author authorOne = new Author(1, "Author1");
	static Publisher publisherOne = new Publisher(1, "Publisher1", "Address1");
	static BookService bookService;

	@Before
	public void before() {
		books.put(bookOne.getId(), bookOne);
		authors.put(authorOne.getId(), authorOne);
		publishers.put(publisherOne.getId(), publisherOne);
	}
	
	@Test
	public void test() {
		stdIn.provideLines("Book2","1","1");
		Scanner scanner = new Scanner(System.in);
		bookService = BookService.getInstance(books, authors, publishers, scanner);
		bookService.create();
		assertTrue(bookService.getModified());
	}
}

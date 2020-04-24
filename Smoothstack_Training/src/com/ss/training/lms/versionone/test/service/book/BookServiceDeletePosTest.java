package com.ss.training.lms.versionone.test.service.book;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.ss.training.lms.versionone.entities.Author;
import com.ss.training.lms.versionone.entities.Book;
import com.ss.training.lms.versionone.entities.Publisher;
import com.ss.training.lms.versionone.services.BookService;

/**
 * @author Justin O'Brien
 *
 */
public class BookServiceDeletePosTest {
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
		stdIn.provideLines("1");
		BookService.getInstance(books, authors, publishers, scanner).delete();
		assertEquals(books.size(), 0);
	}
}

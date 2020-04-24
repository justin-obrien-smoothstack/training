package com.ss.training.lms.versionone.test.service.publisher;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;

import com.ss.training.lms.versionone.entities.Author;
import com.ss.training.lms.versionone.entities.Book;
import com.ss.training.lms.versionone.entities.Publisher;
import com.ss.training.lms.versionone.services.PublisherService;

/**
 * @author Justin O'Brien
 *
 */
public class PublisherServiceReadNegTest {
	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Book bookOne, bookTwo;
	static Author authorOne, authorTwo;
	static Publisher publisherOne, publisherTwo;
	static Scanner scanner;

	@Rule
	public final SystemOutRule stdOut = new SystemOutRule().enableLog();

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
		scanner = new Scanner(System.in);
	}

	@Test
	public void test() { // passed
		PublisherService.getInstance(books, authors, publishers, scanner).read();
		assertEquals(stdOut.getLogWithNormalizedLineSeparator(), "\n");
	}
}

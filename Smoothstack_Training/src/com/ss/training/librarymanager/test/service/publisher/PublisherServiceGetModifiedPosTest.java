package com.ss.training.librarymanager.test.service.publisher;

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
import com.ss.training.librarymanager.services.PublisherService;

/**
 * @author Justin O'Brien
 *
 */
public class PublisherServiceGetModifiedPosTest {
	
	@Rule
	public final TextFromStandardInputStream stdIn = TextFromStandardInputStream.emptyStandardInputStream();

	static HashMap<Long, Book> books = new HashMap<Long, Book>();
	static HashMap<Long, Author> authors = new HashMap<Long, Author>();
	static HashMap<Long, Publisher> publishers = new HashMap<Long, Publisher>();
	static Book bookOne = new Book(1, "Book1", 1, 1);
	static Author authorOne = new Author(1, "Author1");
	static Publisher publisherOne = new Publisher(1, "Publisher1", "Address1");
	static PublisherService publisherService;

	@Before
	public void before() {
		books.put(bookOne.getId(), bookOne);
		authors.put(authorOne.getId(), authorOne);
		publishers.put(publisherOne.getId(), publisherOne);
	}
	
	@Test
	public void test() { // passed
		stdIn.provideLines("Publisher2","Address2");
		Scanner scanner = new Scanner(System.in);
		publisherService = PublisherService.getInstance(books, authors, publishers, scanner);
		publisherService.create();
		assertTrue(publisherService.getModified());
	}
}

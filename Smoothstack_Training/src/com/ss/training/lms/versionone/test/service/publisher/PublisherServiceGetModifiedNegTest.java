package com.ss.training.lms.versionone.test.service.publisher;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Scanner;

import com.ss.training.lms.versionone.entities.Author;
import com.ss.training.lms.versionone.entities.Book;
import com.ss.training.lms.versionone.entities.Publisher;
import com.ss.training.lms.versionone.services.PublisherService;

/**
 * @author Justin O'Brien
 *
 */
public class PublisherServiceGetModifiedNegTest {
	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Scanner scanner;
	static PublisherService publisherService = PublisherService.getInstance(books, authors, publishers, scanner);

	public void test() { // passed
		assertFalse(publisherService.getModified());
	}
}

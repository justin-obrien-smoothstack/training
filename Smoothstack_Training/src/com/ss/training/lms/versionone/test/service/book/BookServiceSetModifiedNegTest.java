package com.ss.training.lms.versionone.test.service.book;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

import com.ss.training.lms.versionone.entities.Author;
import com.ss.training.lms.versionone.entities.Book;
import com.ss.training.lms.versionone.entities.Publisher;
import com.ss.training.lms.versionone.services.BookService;

/**
 * @author Justin O'Brien
 *
 */
public class BookServiceSetModifiedNegTest {
	
	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Scanner scanner;
	static BookService bookService;
	
	@Test
	public void test() { // passed
		BookService bookService = BookService.getInstance(books, authors, publishers, scanner);
		bookService.setModified(true);
		bookService.setModified(false);
		assertFalse(bookService.getModified());
	}

}

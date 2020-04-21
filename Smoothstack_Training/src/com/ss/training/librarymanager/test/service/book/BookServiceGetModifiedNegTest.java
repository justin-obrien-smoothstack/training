package com.ss.training.librarymanager.test.service.book;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;
import com.ss.training.librarymanager.services.BookService;

/**
 * @author Justin O'Brien
 *
 */
public class BookServiceGetModifiedNegTest { // passed

	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Scanner scanner;
	static BookService bookService = BookService.getInstance(books, authors, publishers, scanner);

	@Test
	public void test() {
		assertFalse(bookService.getModified());
	}
}

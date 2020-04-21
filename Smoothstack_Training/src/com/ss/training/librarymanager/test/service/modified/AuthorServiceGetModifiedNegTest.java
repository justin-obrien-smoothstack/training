package com.ss.training.librarymanager.test.service.modified;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;
import com.ss.training.librarymanager.services.AuthorService;

/**
 * @author Justin O'Brien
 *
 */
public class AuthorServiceGetModifiedNegTest {

	static HashMap<Long, Book> books;
	static HashMap<Long, Author> authors;
	static HashMap<Long, Publisher> publishers;
	static Scanner scanner;
	static AuthorService authorService = AuthorService.getInstance(books, authors, publishers, scanner);

	@Test
	public void test() { // passed
		assertFalse(authorService.getModified());
	}
}

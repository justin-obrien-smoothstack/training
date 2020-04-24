package com.ss.training.lms.versionone.test.service.author;

import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Scanner;

import org.junit.Test;

import com.ss.training.lms.versionone.entities.Author;
import com.ss.training.lms.versionone.entities.Book;
import com.ss.training.lms.versionone.entities.Publisher;
import com.ss.training.lms.versionone.services.AuthorService;

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

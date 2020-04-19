package com.ss.training.librarymanager.test.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.ss.training.librarymanager.services.BookService;

/**
 * @author Justin O'Brien
 *
 */

@RunWith(Suite.class)
public class BookServiceTest {

	@Test
	public void createTest() {
		BookService bookService = BookService.getInstance(books, authors, publishers, scanner);
		assertEquals(, actual);
	}

}

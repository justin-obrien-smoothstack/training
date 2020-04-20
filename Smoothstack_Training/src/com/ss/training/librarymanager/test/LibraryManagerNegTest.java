package com.ss.training.librarymanager.test;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.ss.training.librarymanager.LibraryManager;
import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;

/**
 * @author Justin O'Brien
 *
 */

public class LibraryManagerNegTest {

	@Rule
	public final TextFromStandardInputStream stdIn = TextFromStandardInputStream.emptyStandardInputStream();

	@Before
	public void before() throws IOException {
		Path src, dst;
		for (String name : new String[] { "B", "A", "P" }) {
			src = Paths.get("resources/LibraryManager/TestFiles/" + name + ".ser");
			dst = Paths.get("resources/LibraryManager/" + name + ".ser");
			Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	@After
	public void after() throws IOException {
		Path src, dst;
		for (String name : new String[] { "B", "A", "P" }) {
			src = Paths.get("resources/LibraryManager/DemoFiles/" + name + ".ser");
			dst = Paths.get("resources/LibraryManager/" + name + ".ser");
			Files.copy(src, dst, StandardCopyOption.REPLACE_EXISTING);
		}
	}

	@Test
	public void negTest() throws IOException, ClassNotFoundException {
		stdIn.provideLines("");
		String path = "resources/LibraryManager/";
		Book expectedBook = new Book(1, "Book1", 1, 1), actualBook;
		Author expectedAuthor = new Author(1, "Author1"), actualAuthor;
		Publisher expectedPublisher = new Publisher(1, "Publisher1", "Address1"), actualPublisher;
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "B.ser"))) {
			actualBook = (Book) inFile.readObject();
		}
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "A.ser"))) {
			actualAuthor = (Author) inFile.readObject();
		}
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "P.ser"))) {
			actualPublisher = (Publisher) inFile.readObject();
		}
		LibraryManager.main(null);
		assertEquals(expectedBook.getId(), actualBook.getId());
		assertEquals(expectedBook.getTitle(), actualBook.getTitle());
		assertEquals(expectedBook.getAuthor(), actualBook.getAuthor());
		assertEquals(expectedBook.getPublisher(), actualBook.getPublisher());
		assertEquals(expectedAuthor.getId(), actualAuthor.getId());
		assertEquals(expectedAuthor.getName(), actualAuthor.getName());
		assertEquals(expectedPublisher.getId(), actualPublisher.getId());
		assertEquals(expectedPublisher.getName(), actualPublisher.getName());
		assertEquals(expectedPublisher.getAddress(), actualPublisher.getAddress());
	}
}

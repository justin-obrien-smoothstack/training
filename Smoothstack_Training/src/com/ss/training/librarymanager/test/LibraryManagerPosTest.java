package com.ss.training.librarymanager.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
public class LibraryManagerPosTest {

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
	public void negTest() throws IOException, ClassNotFoundException { // passed
		stdIn.provideLines("b","u","1","t","Title1","");
		String path = "resources/LibraryManager/";
		Book initialBook, actualBook;
		Author expectedAuthor, actualAuthor;
		Publisher expectedPublisher, actualPublisher;
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "B.ser"))) {
			initialBook = (Book) inFile.readObject();
		}
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "A.ser"))) {
			expectedAuthor = (Author) inFile.readObject();
		}
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "P.ser"))) {
			expectedPublisher = (Publisher) inFile.readObject();
		}
		LibraryManager.main(null);
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "B.ser"))) {
			actualBook = (Book) inFile.readObject();
		}
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "A.ser"))) {
			actualAuthor = (Author) inFile.readObject();
		}
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream(path + "P.ser"))) {
			actualPublisher = (Publisher) inFile.readObject();
		}
		assertEquals(initialBook.getId(), actualBook.getId());
		assertNotEquals(initialBook.getTitle(), actualBook.getTitle());
		assertEquals("Title1", actualBook.getTitle());
		assertEquals(initialBook.getAuthor(), actualBook.getAuthor());
		assertEquals(initialBook.getPublisher(), actualBook.getPublisher());
		assertEquals(expectedAuthor.getId(), actualAuthor.getId());
		assertEquals(expectedAuthor.getName(), actualAuthor.getName());
		assertEquals(expectedPublisher.getId(), actualPublisher.getId());
		assertEquals(expectedPublisher.getName(), actualPublisher.getName());
		assertEquals(expectedPublisher.getAddress(), actualPublisher.getAddress());
	}
}

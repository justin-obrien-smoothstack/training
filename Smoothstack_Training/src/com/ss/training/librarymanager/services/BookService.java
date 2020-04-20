package com.ss.training.librarymanager.services;

import java.util.HashMap;
import java.util.Scanner;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;

/**
 * This singleton class performs CRUD operations on Book objects.
 * 
 * @author Justin O'Brien
 *
 */
public class BookService implements Service {
	public static BookService instance = null;
	boolean modified = false;
	private HashMap<Long, Book> books;
	private HashMap<Long, Author> authors;
	private HashMap<Long, Publisher> publishers;
	private Scanner scanner;

	private BookService(HashMap<Long, Book> books, HashMap<Long, Author> authors, HashMap<Long, Publisher> publishers,
			Scanner scanner) {
		this.books = books;
		this.authors = authors;
		this.publishers = publishers;
		this.scanner = scanner;
	}

	public static BookService getInstance(HashMap<Long, Book> books, HashMap<Long, Author> authors,
			HashMap<Long, Publisher> publishers, Scanner scanner) {
		if (instance == null)
			instance = new BookService(books, authors, publishers, scanner);
		return instance;
	}

	/**
	 * @return the instance
	 */
	public static BookService getInstance() {
		return instance;
	}

	/**
	 * @return the modified
	 */
	public boolean getModified() {
		return modified;
	}

	/**
	 * @param modified the modified to set
	 */
	public void setModified(boolean modified) {
		this.modified = modified;
	}

	public void create() {
		String title;
		long id, authorId, publisherId;
		if ((id = nextBookId(books)) <= 0) {
			lmsFullErrorPrinter.print("books");
			return;
		}
		infoPrompter.print("title", "book", "create", "a blank line");
		if ("".equals(title = scanner.nextLine()))
			return;
		if ((authorId = inputAuthorId("author of the book", "create", authors, scanner)) <= 0)
			return;
		if ((publisherId = inputPublisherId("publisher of the book", "create", publishers, scanner)) <= 0)
			return;
		Book book = new Book(id, title, authorId, publisherId);
		books.put(book.getId(), book);
		modified = true;
		System.out.println("Book created.");
	}

	public void read() {
		System.out.println();
		books.keySet().stream().sorted().forEach(id -> {
			Book book = books.get(id);
			System.out.println("ID number: " + book.getId());
			System.out.println("Title:     " + book.getTitle());
			System.out.println("Author:    " + authors.get(book.getAuthor()).getName());
			System.out.println("Publisher: " + publishers.get(book.getPublisher()).getName());
			System.out.println();
		});
	}

	public void update() {
		long id, newId;
		String newTitle;
		if ((id = inputBookId("book", "update", books, scanner)) <= 0)
			return;
		System.out.println("What information do you want to update?");
		optionsPrinter.print(new String[][] { { "T", "Title" }, { "A", "Author ID" }, { "P", "Publisher ID" } });
		optionPrompter.print();
		switch (scanner.nextLine().toLowerCase()) {
		case "t":
		case "title":
			infoPrompter.print("title", "book", "update", "a blank line");
			if ("".equals(newTitle = scanner.nextLine()))
				return;
			books.get(id).setTitle(newTitle);
			break;
		case "a":
		case "author id":
			if ((newId = inputAuthorId("author of the book", "update", authors, scanner)) <= 0)
				return;
			books.get(id).setAuthor(newId);
			break;
		case "p":
		case "publisher id":
			if ((newId = inputPublisherId("publisher of the book", "update", publishers, scanner)) <= 0)
				return;
			books.get(id).setPublisher(newId);
			break;
		default:
			return;
		}
		modified = true;
		System.out.println("Book updated.");
	}

	public void delete() {
		long id;
		if ((id = inputBookId("book", "delete", books, scanner)) <= 0)
			return;
		books.remove(id);
		modified = true;
		System.out.println("Book deleted.");
	}
}

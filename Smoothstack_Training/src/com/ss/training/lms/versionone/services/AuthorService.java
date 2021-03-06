package com.ss.training.lms.versionone.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

import com.ss.training.lms.versionone.entities.Author;
import com.ss.training.lms.versionone.entities.Book;
import com.ss.training.lms.versionone.entities.Publisher;

/**
 * This singleton class performs CRUD operations on Author objects.
 * 
 * @author Justin O'Brien
 *
 */
public class AuthorService implements Service {
	private static AuthorService instance = null;
	boolean modified = false;
	private HashMap<Long, Book> books;
	private HashMap<Long, Author> authors;
	private HashMap<Long, Publisher> publishers;
	private Scanner scanner;

	private AuthorService(HashMap<Long, Book> books, HashMap<Long, Author> authors, HashMap<Long, Publisher> publishers,
			Scanner scanner) {
		this.books = books;
		this.authors = authors;
		this.publishers = publishers;
		this.scanner = scanner;
	}

	public static AuthorService getInstance(HashMap<Long, Book> books, HashMap<Long, Author> authors,
			HashMap<Long, Publisher> publishers, Scanner scanner) {
		if (instance == null)
			instance = new AuthorService(books, authors, publishers, scanner);
		return instance;
	}

	/**
	 * @return the modified
	 */
	public boolean getModified() {
		return modified;
	}

	public void create() {
		long id;
		String name;
		if ((id = nextAuthorId(authors)) <= 0) {
			lmsFullErrorPrinter.print("authors");
			return;
		}
		infoPrompter.print("name", "author", "create", "a blank line");
		if ("".equals(name = scanner.nextLine()))
			return;
		Author author = new Author(id, name);
		authors.put(author.getId(), author);
		modified = true;
		System.out.println("Author created.");
	}

	public void read() {
		System.out.println();
		authors.keySet().stream().sorted().forEach(id -> {
			Author author = authors.get(id);
			System.out.println("ID number: " + author.getId());
			System.out.println("Name:      " + author.getName());
			System.out.println();
		});

	}

	public void update() {
		long id;
		String name;
		if ((id = inputAuthorId("author", "update", authors, scanner)) <= 0)
			return;
		infoPrompter.print("name", "author", "update", "a blank line");
		if ("".equals(name = scanner.nextLine()) || name == null)
			return;
		authors.get(id).setName(name);
		modified = true;
		System.out.println("Author updated.");
	}

	public void delete() {
		long id;
		HashSet<Long> booksToRemoveIds = new HashSet<Long>();
		if ((id = inputAuthorId("author", "delete", authors, scanner)) <= 0)
			return;
		if (books.values().stream().filter(book -> book.getAuthor() == id).count() != 0) {
			deletionWarner.print("written", "author", confirmer);
			if (!confirmer.equals(scanner.nextLine()))
				return;
			books.values().stream().filter(book -> book.getAuthor() == id).forEach(book -> {
				booksToRemoveIds.add(book.getId());
			});
			booksToRemoveIds.stream().forEach(bookId -> {
				books.remove(bookId);
			});
			BookService.getInstance(books, authors, publishers, scanner).setModified(true);
		}
		authors.remove(id);
		modified = true;
		System.out.println("Author deleted.");
	}
}

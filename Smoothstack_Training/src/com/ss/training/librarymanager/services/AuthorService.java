package com.ss.training.librarymanager.services;

import java.util.HashMap;
import java.util.Scanner;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;

/**
 * This singleton class performs CRUD operations on Author objects.
 * 
 * @author Justin O'Brien
 *
 */
public class AuthorService implements Service {
	public static AuthorService instance = null;
	private HashMap<Long, Book> books;
	private HashMap<Long, Author> authors;
	private Scanner scanner;

	private AuthorService(HashMap<Long, Book> books, HashMap<Long, Author> authors, Scanner scanner) {
		this.books = books;
		this.authors = authors;
		this.scanner = scanner;
	}

	public static AuthorService getInstance(HashMap<Long, Book> books, HashMap<Long, Author> authors, Scanner scanner) {
		if (instance == null)
			instance = new AuthorService(books, authors, scanner);
		return instance;
	}

	public void create() {
		long id;
		String name;
		if((id = nextAuthorId(authors)) <= 0) {
			lmsFullErrorPrinter.print("authors");
			return;
		}
		infoPrompter.print("name", "author", "create", "a blank line");
		if ("".equals(name = scanner.nextLine()))
			return;
		Author author = new Author(id, name);
		authors.put(author.getId(), author);
		System.out.println("Author created.");
	}

	public void read() {
		authors.keySet().stream().sorted().forEach(id -> {
			Author author = authors.get(id);
			System.out.println("ID number:" + delimiter + author.getId());
			System.out.println("Name:" + delimiter + author.getName());
			System.out.println();
		});

	}

	public void update() {
		long id;
		String name;
		if ((id = inputAuthorId("author", "update", authors, scanner)) <= 0)
			return;
		infoPrompter.print("name", "author", "update", "a blank line");
		if ("".equals(name = scanner.nextLine()))
			return;
		authors.get(id).setName(name);
		System.out.println("Author updated.");
	}

	public void delete() {
		long id;
		if ((id = inputAuthorId("author", "delete", authors, scanner)) <= 0)
			return;
		if (books.values().stream().filter(book -> book.getAuthor() == id).count() != 0) {
			deletionWarner.print("written", "author", confirmer);
			if (!confirmer.equals(scanner.nextLine()))
				return;
		}
		authors.remove(id);
		books.values().stream().filter(book -> book.getAuthor() == id).forEach(book -> {
			books.remove(book.getId());
		});
		System.out.println("Author deleted.");
	}
}

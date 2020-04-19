package com.ss.training.librarymanager.services;

import java.util.HashMap;
import java.util.Scanner;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;

/**
 * This singleton class performs CRUD operations on Publisher objects.
 * 
 * @author Justin O'Brien
 *
 */
public class PublisherService implements Service {
	public static PublisherService instance = null;
	boolean modified = false;
	private HashMap<Long, Book> books;
	private HashMap<Long, Author> authors;
	private HashMap<Long, Publisher> publishers;
	private Scanner scanner;

	private PublisherService(HashMap<Long, Book> books, HashMap<Long, Author> authors,
			HashMap<Long, Publisher> publishers, Scanner scanner) {
		this.books = books;
		this.authors = authors;
		this.publishers = publishers;
		this.scanner = scanner;
	}

	public static PublisherService getInstance(HashMap<Long, Book> books, HashMap<Long, Author> authors,
			HashMap<Long, Publisher> publishers, Scanner scanner) {
		if (instance == null)
			instance = new PublisherService(books, authors, publishers, scanner);
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
		String name, address;
		if ((id = nextPublisherId(publishers)) <= 0) {
			lmsFullErrorPrinter.print("publishers");
			return;
		}
		infoPrompter.print("name", "publisher", "create", "a blank line");
		if ("".equals(name = scanner.nextLine()))
			return;
		infoPrompter.print("address", "publisher", "create", "a blank line");
		if ("".equals(address = scanner.nextLine()))
			return;
		Publisher publisher = new Publisher(id, name, address);
		publishers.put(publisher.getId(), publisher);
		modified = true;
		System.out.println("Publisher created.");
	}

	public void read() {
		publishers.keySet().stream().sorted().forEach(id -> {
			Publisher publisher = publishers.get(id);
			System.out.println("ID number:" + delimiter + publisher.getId());
			System.out.println("Name:" + delimiter + publisher.getName());
			System.out.println("Address:" + delimiter + publisher.getAddress());
			System.out.println();
		});
	}

	public void update() {
		long id;
		String newInfo;
		if ((id = inputPublisherId("publisher", "update", publishers, scanner)) <= 0)
			return;
		System.out.println("What information do you want to update?");
		optionsPrinter.print(new String[][] { { "N", "Name" }, { "A", "Address" } });
		optionPrompter.print();
		switch (scanner.nextLine().toLowerCase()) {
		case "n":
		case "name":
			infoPrompter.print("name", "publisher", "update", "a blank line");
			if ("".equals(newInfo = scanner.nextLine()))
				return;
			publishers.get(id).setName(newInfo);
			break;
		case "a":
		case "address":
			infoPrompter.print("address", "publisher", "update", "a blank line");
			if ("".equals(newInfo = scanner.nextLine()))
				return;
			publishers.get(id).setAddress(newInfo);
			break;
		default:
			return;
		}
		modified = true;
		System.out.println("Publisher updated.");
	}

	public void delete() {
		long id;
		if ((id = inputPublisherId("publisher", "delete", publishers, scanner)) <= 0)
			return;
		if (books.values().stream().filter(book -> book.getPublisher() == id).count() != 0) {
			deletionWarner.print("published", "publisher", confirmer);
			if (!confirmer.equals(scanner.nextLine()))
				return;
			books.values().stream().filter(book -> book.getPublisher() == id).forEach(book -> {
				books.remove(book.getId());
			});
			BookService.getInstance(books, authors, publishers, scanner).setModified(true);
		}
		publishers.remove(id);
		modified = true;
		System.out.println("Publisher deleted.");
	}
}

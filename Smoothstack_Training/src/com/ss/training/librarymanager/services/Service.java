package com.ss.training.librarymanager.services;

import java.util.HashMap;
import java.util.Scanner;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;
import com.ss.training.librarymanager.functionalinterfaces.MessagePrinter;
import com.ss.training.librarymanager.functionalinterfaces.OptionsPrinter;

/**
 * This interface is implemented by singleton classes that perform CRUD
 * operations on files containing information on the user's library.
 * 
 * @author Justin O'Brien
 *
 */
public interface Service {

	String confirmer = "OK";

	MessagePrinter infoPrompter = (strings) -> {
		System.out.print("Enter the " + strings[0] + " of the " + strings[1] + " to " + strings[2] + ", or enter "
				+ strings[3] + " to cancel the operation: ");
	},

			lmsFullErrorPrinter = (strings) -> {
				System.out.println("Error: The library management system has no room for more " + strings[0] + ".");
			},

			idErrorPrinter = (strings) -> {
				System.out.println("Error: There exists no " + strings[0] + " with ID number " + strings[1] + ".");
			},

			optionPrompter = (strings) -> {
				System.out.print(
						"Enter any of the above to make a selection, or enter anything else to cancel the operation: ");
			},

			deletionWarner = (strings) -> System.out.print("Warning: This will cause all books " + strings[0]
					+ " by this " + strings[1] + " to be deleted as well. Enter " + strings[2]
					+ " to proceed, or enter anything else to cancel the operation: ");

	OptionsPrinter optionsPrinter = opts -> {
		for (String[] opt : opts)
			System.out.println(opt[0] + '\t' + opt[1]);
	};

	default long nextBookId(HashMap<Long, Book> books) {
		for (long i = 1;; i++) {
			if (!books.containsKey(i))
				return i;
		}
	}

	default long nextAuthorId(HashMap<Long, Author> authors) {
		for (long i = 1;; i++) {
			if (!authors.containsKey(i))
				return i;
		}
	}

	default long nextPublisherId(HashMap<Long, Publisher> publishers) {
		for (long i = 1;; i++) {
			if (!publishers.containsKey(i))
				return i;
		}
	}

	default long inputBookId(String entity, String action, HashMap<Long, Book> books, Scanner scanner) {
		long id;
		while (true) {
			infoPrompter.print("ID number", entity, action, "anything other than a positive integer");
			try {
				id = Long.parseLong(scanner.nextLine());
			} catch (NumberFormatException e) {
				return 0;
			}
			if (id <= 0 || books.containsKey(id))
				return id;
			idErrorPrinter.print("book", Long.toString(id));
		}
	}

	default long inputAuthorId(String entity, String action, HashMap<Long, Author> authors, Scanner scanner) {
		long id;
		while (true) {
			infoPrompter.print("ID number", entity, action, "anything other than a positive integer");
			try {
				id = Long.parseLong(scanner.nextLine());
			} catch (NumberFormatException e) {
				return 0;
			}
			if (id <= 0 || authors.containsKey(id))
				return id;
			idErrorPrinter.print("author", Long.toString(id));
		}
	}

	default long inputPublisherId(String entity, String action, HashMap<Long, Publisher> publishers, Scanner scanner) {
		long id;
		while (true) {
			infoPrompter.print("ID number", entity, action, "anything other than a positive integer");
			try {
				id = Long.parseLong(scanner.nextLine());
			} catch (NumberFormatException e) {
				return 0;
			}
			if (id <= 0 || publishers.containsKey(id))
				return id;
			idErrorPrinter.print("publisher", Long.toString(id));
		}
	}

	public boolean getModified();
	
	public void create(); // creates a new entry in the file

	public void read(); // reads entries from the file

	public void update(); // updates an existing entry in the file

	public void delete(); // deletes an entry from the file
}

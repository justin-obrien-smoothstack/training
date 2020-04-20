package com.ss.training.librarymanager;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

import com.ss.training.librarymanager.entities.Author;
import com.ss.training.librarymanager.entities.Book;
import com.ss.training.librarymanager.entities.Publisher;
import com.ss.training.librarymanager.functionalinterfaces.MessagePrinter;
import com.ss.training.librarymanager.functionalinterfaces.OptionsPrinter;
import com.ss.training.librarymanager.services.AuthorService;
import com.ss.training.librarymanager.services.BookService;
import com.ss.training.librarymanager.services.PublisherService;
import com.ss.training.librarymanager.services.Service;

/**
 * This class enables a user to perform CRUD operations on three files, each of
 * which contains information on books, authors, or publishers.
 * 
 * @author Justin O'Brien
 */
public class LibraryManager {
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		int i;
		char optsDelimiter = '\t';
		String dirPath = "resources/LibraryManager/", ext = ".ser", opt;
		String[][] entitiesOpts = { { "B", "Book" }, { "A", "Author" }, { "P", "Publisher" } },
				opsOpts = { { "C", "Create" }, { "R", "Read" }, { "U", "Update" }, { "D", "Delete" } };
		StringBuilder filePath = new StringBuilder(dirPath + ext);
		File file;
		HashMap<Long, Book> books = new HashMap<Long, Book>();
		HashMap<Long, Author> authors = new HashMap<Long, Author>();
		HashMap<Long, Publisher> publishers = new HashMap<Long, Publisher>();
		Book book;
		Author author;
		Publisher publisher;
		BookService bookService;
		AuthorService authorService;
		PublisherService publisherService;
		Service service;
		Service[] services;
		OptionsPrinter optionsPrinter = opts -> {
			for (String[] thisOpt : opts)
				System.out.println(thisOpt[0] + optsDelimiter + thisOpt[1]);
		};
		MessagePrinter optionsPromptPrinter = action -> System.out
				.print("Enter any of the above to make a selection, or enter anything else to " + action[0] + ": ");
		MessagePrinter fileErrorPrinter = strings -> System.out
				.println("Error: Could not" + strings[0] + " the file " + strings[1] + ".");
		try (Scanner scanner = new Scanner(System.in)) {
			bookService = BookService.getInstance(books, authors, publishers, scanner);
			authorService = AuthorService.getInstance(books, authors, publishers, scanner);
			publisherService = PublisherService.getInstance(books, authors, publishers, scanner);
			services = new Service[] { bookService, authorService, publisherService };
			try {
				for (String[] entityOpts : entitiesOpts) {
					filePath.replace(dirPath.length(), filePath.length() - ext.length(), entityOpts[0]);
					file = new File(filePath.toString());
					if (file.exists()) {
						try (ObjectInputStream inputFile = new ObjectInputStream(new FileInputStream(file))) {
							switch (entityOpts[0]) {
							case "B":
								while (true) {
									try {
										book = (Book) inputFile.readObject();
										books.put(book.getId(), book);
									} catch (EOFException e) {
										break;
									}
								}
								break;
							case "A":
								while (true) {
									try {
										author = (Author) inputFile.readObject();
										authors.put(author.getId(), author);
									} catch (EOFException e) {
										break;
									}
								}
								break;
							case "P":
								while (true) {
									try {
										publisher = (Publisher) inputFile.readObject();
										publishers.put(publisher.getId(), publisher);
									} catch (EOFException e) {
										break;
									}
								}
								break;
							}
						} catch (Exception e) {
							fileErrorPrinter.print("read from", filePath.toString());
							return;
						}
					}
				}
				while (true) {
					System.out.println("What information do you want to operate on?");
					optionsPrinter.print(entitiesOpts);
					optionsPromptPrinter.print("exit");
					switch (scanner.nextLine().toLowerCase()) {
					case "b":
					case "book":
						service = bookService;
						break;
					case "a":
					case "author":
						service = authorService;
						break;
					case "p":
					case "publisher":
						service = publisherService;
						break;
					default:
						return;
					}
					System.out.println("What operation do you want to do?");
					optionsPrinter.print(opsOpts);
					optionsPromptPrinter.print("go back");
					switch (scanner.nextLine().toLowerCase()) {
					case "c":
					case "create":
						service.create();
						break;
					case "r":
					case "read":
						service.read();
						break;
					case "u":
					case "update":
						service.update();
						break;
					case "d":
					case "delete":
						service.delete();
						break;
					}
				}
			} finally {
				for (i = 0; i < services.length; i++) {
					if (!services[i].getModified())
						continue;
					opt = entitiesOpts[i][0];
					filePath.replace(dirPath.length(), filePath.length() - ext.length(), opt);
					file = new File(filePath.toString());
					try (ObjectOutputStream outputFile = new ObjectOutputStream(new FileOutputStream(file))) {
						switch (opt) {
						case "B":
							books.forEach((id, thisBook) -> {
								try {
									outputFile.writeObject(thisBook);
								} catch (Exception e) {
									fileErrorPrinter.print("write book #" + id + " to", filePath.toString());
								}
							});
							break;
						case "A":
							authors.forEach((id, thisAuthor) -> {
								try {
									outputFile.writeObject(thisAuthor);
								} catch (Exception e) {
									fileErrorPrinter.print("write author #" + id + " to", filePath.toString());
								}
							});
							break;
						case "P":
							publishers.forEach((id, thisPublisher) -> {
								try {
									outputFile.writeObject(thisPublisher);
								} catch (Exception e) {
									fileErrorPrinter.print("write publisher #" + id + " to", filePath.toString());
								}
							});
							break;
						}
					} catch (Exception e) {
						fileErrorPrinter.print("write to", filePath.toString());
					}
				}
			}
		}
	}
}

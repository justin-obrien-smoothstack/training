package com.ss.training.lms.versiontwo.presentation;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.AdminService;
import com.ss.training.lms.versiontwo.object.Author;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;
import com.ss.training.lms.versiontwo.object.Genre;
import com.ss.training.lms.versiontwo.object.HasCopiesAndIntegerId;
import com.ss.training.lms.versiontwo.object.HasLoansAndIntegerId;
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;
import com.ss.training.lms.versiontwo.object.Publisher;

/**
 * @author Justin O'Brien
 */
public class PresCrud {

	private final String operationCancelled = "The operation was cancelled.";

	private AdminService adminService = new AdminService();

	private String udpatePrompt(String objectType) {
		return "Which " + objectType + " do you want to update?";
	}

	private String deletePrompt(String objectType) {
		return "Which " + objectType + " do you want to delete?";
	}

	private String secondUpdatePrompt(String objectType) {
		return "Which information do you want to update for this " + objectType + "?";
	}

	private boolean getYesOrNo(String prompt) {
		if (PresUtils.getOptionSelection(prompt, PresUtils.newArrayList("No", "Yes")) == 1)
			return true;
		return false;
	}

	private LocalDateTime getPastDate(String prompt) {
		String input;
		for (;;) {
			input = Presentation.scanner.nextLine();
			LocalDateTime output;
			try {
				output = LocalDateTime.parse(input);
			} catch (DateTimeParseException e) {
				System.out.println("Error: That is not a valid date.");
				continue;
			}
			if (!LocalDateTime.now().isBefore(output))
				return output;
			System.out.println("Error: That date is in the future.");
		}
	}

	protected int getOptionSelection(String prompt, List<String> options) {
		int i, selectionNumber;
		for (;;) {
			System.out.println(prompt);
			for (i = 0; i < options.size(); i++) {
				System.out.println((i + 1) + ") " + options.get(i));
			}
			try {
				selectionNumber = Integer.parseInt(Presentation.scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(Presentation.invalidSelection);
				continue;
			}
			if (0 < selectionNumber && selectionNumber <= options.size())
				return selectionNumber;
			System.out.println(Presentation.invalidSelection);
		}
	}

	protected ArrayList<Integer> getMultiOptionSelection(String prompt, ArrayList<String> options) {
		int i, selectionNumber;
		String[] userInput;
		ArrayList<Integer> selectedOptions = new ArrayList<Integer>();
		outerLoop: for (;;) {
			System.out.println(prompt);
			for (i = 0; i < options.size(); i++) {
				System.out.println((i + 1) + ") " + options.get(i));
			}
			userInput = Presentation.scanner.nextLine().split(" ");
			if (userInput[0].isEmpty())
				return selectedOptions;
			for (String selectedOption : userInput) {
				try {
					selectionNumber = Integer.parseInt(selectedOption);
				} catch (NumberFormatException e) {
					System.out.println(Presentation.invalidSelection);
					selectedOptions.clear();
					continue outerLoop;
				}
				if (selectionNumber < 1 || selectionNumber > options.size()) {
					System.out.println(Presentation.invalidSelection);
					selectedOptions.clear();
					continue outerLoop;
				}
				if (!selectedOptions.contains(selectionNumber))
					selectedOptions.add(selectionNumber);
			}
			return selectedOptions;
		}
	}

	protected LMSObject getObjectSelection(String prompt, ArrayList<LMSObject> lmsObjects) {
		int selectedOption;
		ArrayList<String> options = lmsObjects.stream().map(object -> object.getDisplayName())
				.collect(Collectors.toCollection(ArrayList::new));
		selectedOption = getOptionSelection(prompt, options);
		return lmsObjects.get(selectedOption - 1);
	}

	private ArrayList<?> getMultiObjectSelection(String prompt, ArrayList<LMSObject> objects) {
		ArrayList<LMSObject> selectedObjects = new ArrayList<LMSObject>();
		ArrayList<String> options = objects.stream().map(object -> object.getDisplayName())
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Integer> selectedNumbers = getMultiOptionSelection(
				prompt + "\nEnter all that apply, separated by spaces.", options);
		selectedNumbers.stream().forEach(number -> selectedObjects.add(objects.get(number - 1)));
		return selectedObjects;
	}

	private void addLoans(HasLoansAndIntegerId object, String objectType) {
		int id = object.getId();
		Loan loan;
		ArrayList<Loan> loans = object.getLoans();
		do {
			loan = new Loan();
			if (LMS.borrower.equals(objectType)) {
				if (id != 0)
					loan.setCardNo(id);
			} else
				loan.setCardNo(((Borrower) getObjectSelection("Who checked out the book?",
						(ArrayList<LMSObject>) adminService.getAllObjects(LMS.borrower))).getCardNo());
			if (LMS.branch.equals(objectType)) {
				if (id != 0)
					loan.setBranchId(id);
			} else
				loan.setBranchId(((Branch) getObjectSelection("What branch was the book checked out from?",
						(ArrayList<LMSObject>) adminService.getAllObjects(LMS.branch))).getId());
			if (LMS.book.equals(objectType)) {
				if (id != 0)
					loan.setBookId(id);
			} else
				loan.setBookId(((Book) getObjectSelection("What book was checked out?",
						(ArrayList<LMSObject>) adminService.getAllObjects(LMS.book))).getId());
			loan.setDateOut(getPastDate("When was the book checked out?"));
			if (loans.contains(loan)) {
				System.out.println("Error: That loan is already documented.");
				continue;
			}
			loan.setDueDate(loan.getDateOut().plusDays(7));
			if (getYesOrNo("Has the book been returned?")) {
				loan.setDateIn(getPastDate("When was the book returned?"));
			}
			loans.add(loan);
		} while (getYesOrNo("Add another loan?"));
	}

	private void addCopies(HasCopiesAndIntegerId object, String objectType) {
		int id = object.getId();
		Copies copies;
		ArrayList<Copies> copieses = object.getCopies();
		do {
			copies = new Copies();
			if (LMS.branch.equals(objectType)) {
				if (id != 0)
					copies.setBranchId(id);
			} else
				copies.setBranchId(((Branch) getObjectSelection("Which branch has copies of the book?",
						(ArrayList<LMSObject>) adminService.getAllObjects(LMS.branch))).getId());
			if (LMS.book.equals(objectType)) {
				if (id != 0)
					copies.setBookId(id);
			} else
				copies.setBookId(((Book) getObjectSelection("Which book does the branch have copies of?",
						(ArrayList<LMSObject>) adminService.getAllObjects(LMS.book))).getId());
			if (copieses.contains(copies)) {
				System.out.println("Error: That is already documented.");
				continue;
			}
			if (getYesOrNo("Do you know how many copies the branch has?")) {
				copies.setCopies(PresUtils.getNaturalNumber("How many copies does the branch have?",
						"Error: That is not a valid number of copies."));
			}
			copieses.add(copies);
		} while (getYesOrNo("Add another number of copies?"));
	}

	private String createAuthor() {
		Author author = new Author();
		ArrayList<LMSObject> allBooks = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.book);
		ArrayList<Book> books;
		author.setName(PresUtils.getStringWithMaxLength("What is the author's name?", "name",
				Presentation.maxStringFieldLength));
		if (allBooks.size() != 0 && getYesOrNo("Has this author written any of the books in our system?")) {
			books = (ArrayList<Book>) getMultiObjectSelection("Which books has this author written?", allBooks);
			author.setBookIds(
					books.stream().map(book -> book.getId()).collect(Collectors.toCollection(ArrayList::new)));
		}
		if (getYesOrNo("Create this author?"))
			return adminService.createAuthor(author);
		return operationCancelled;
	}

	private String createBook() {
		Book book = new Book();
		Publisher publisher;
		ArrayList<LMSObject> allPublishers = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.publisher),
				allAuthors = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.author),
				allGenres = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.genre),
				allBranches = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.branch),
				allBorrowers = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.borrower);
		ArrayList<Author> authors;
		ArrayList<Genre> genres;
		book.setTitle(PresUtils.getStringWithMaxLength("What is the book's title?", "title",
				Presentation.maxStringFieldLength));
		if (allPublishers.size() != 0 && getYesOrNo("Was this book published by any of the publishers in our system?"))
			book.setPubId(((Publisher) getObjectSelection("Who is the book's publisher?", allPublishers)).getId());
		if (allAuthors.size() != 0 && getYesOrNo("Was this book written by any of the authors in our system?")) {
			authors = (ArrayList<Author>) getMultiObjectSelection("Which authors wrote this book?", allAuthors);
			book.setAuthorIds(
					authors.stream().map(author -> author.getId()).collect(Collectors.toCollection(ArrayList::new)));
		}
		if (allGenres.size() != 0 && getYesOrNo("Do any of the genres in our system include this book?")) {
			genres = (ArrayList<Genre>) getMultiObjectSelection("Which genres include this book?", allGenres);
			book.setGenreIds(
					genres.stream().map(genre -> genre.getId()).collect(Collectors.toCollection(ArrayList::new)));
		}
		if (allBranches.size() != 0 && getYesOrNo("Do any branches have copies of this book?"))
			addCopies(book, LMS.book);
		if (allBranches.size() != 0 && allBorrowers.size() != 0
				&& getYesOrNo("Has this book ever been checked out from our library?"))
			addLoans(book, LMS.book);
		if (getYesOrNo("Create this book?"))
			return adminService.createBook(book);
		return operationCancelled;
	}

	private String createBorrower() {
		Borrower borrower = new Borrower();
		ArrayList<LMSObject> allBooks = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.book);
		ArrayList<LMSObject> allBranches = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.branches);
		if (getYesOrNo("Do you know the borrower's name?"))
			borrower.setName(PresUtils.getStringWithMaxLength("What is the borrower's name?", "name",
					Presentation.maxStringFieldLength));
		if (getYesOrNo("Do you know the borrower's address?"))
			borrower.setAddress(PresUtils.getStringWithMaxLength("What is the borrower's address?", "address",
					Presentation.maxStringFieldLength));
		if (getYesOrNo("Do you know the borrower's phone number?"))
			borrower.setAddress(PresUtils.getStringWithMaxLength("What is the borrower's phone number?", "phone number",
					Presentation.maxStringFieldLength));
		if (allBooks.size() != 0 && allBranches.size() != 0
				&& getYesOrNo("Has this borrower ever checked out any books from our library?"))
			addLoans(borrower, LMS.borrower);
		if (getYesOrNo("Create this borrower?"))
			return adminService.createBorrower(borrower);
		return operationCancelled;
	}

	private String createBranch() {
		Branch branch = new Branch();
		ArrayList<LMSObject> allBooks = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.book);
		ArrayList<LMSObject> allBorrowers = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.borrower);
		if (getYesOrNo("Do you know the branch's name?"))
			branch.setName(PresUtils.getStringWithMaxLength("What is the branch's name?", "name",
					Presentation.maxStringFieldLength));
		if (getYesOrNo("Do you know the branch's address?"))
			branch.setAddress(PresUtils.getStringWithMaxLength("What is the branch's address?", "address",
					Presentation.maxStringFieldLength));
		if (allBooks.size() != 0 && getYesOrNo("Does this branch have copies of any books?"))
			addCopies(branch, LMS.branch);
		if (allBooks.size() != 0 && allBorrowers.size() != 0
				&& getYesOrNo("Have any books ever been checked out from this branch?"))
			addLoans(branch, LMS.branch);
		if (getYesOrNo("Create this branch?"))
			return adminService.createBranch(branch);
		return operationCancelled;
	}

	private String createGenre() {
		Genre genre = new Genre();
		ArrayList<LMSObject> allBooks = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.book);
		ArrayList<Book> books;
		genre.setName(PresUtils.getStringWithMaxLength("What is the genre's name?", "name",
				Presentation.maxStringFieldLength));
		if (allBooks.size() != 0 && getYesOrNo("Does this genre Include any of the books in our system?")) {
			books = (ArrayList<Book>) getMultiObjectSelection("Which books are in this genre?", allBooks);
			genre.setBookIds(books.stream().map(book -> book.getId()).collect(Collectors.toCollection(ArrayList::new)));
		}
		if (getYesOrNo("Create this genre?"))
			return adminService.createGenre(genre);
		return operationCancelled;
	}

	private String createPublisher() {
		Publisher publisher = new Publisher();
		ArrayList<LMSObject> allBooks = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.book);
		ArrayList<Book> books;
		publisher.setName(PresUtils.getStringWithMaxLength("What is the publisher's name?", "name",
				Presentation.maxStringFieldLength));
		if (getYesOrNo("Do you know the publisher's address?"))
			publisher.setAddress(PresUtils.getStringWithMaxLength("What is the publisher's address?", "address",
					Presentation.maxStringFieldLength));
		if (getYesOrNo("Do you know the publisher's phone number?"))
			publisher.setAddress(PresUtils.getStringWithMaxLength("What is the publisher's phone number?",
					"phone number", Presentation.maxStringFieldLength));
		if (allBooks.size() != 0 && getYesOrNo("Has this publisher published any of the books in our system?")) {
			books = (ArrayList<Book>) getMultiObjectSelection("Which books has this publisher published?", allBooks);
			publisher.setBookIds(
					books.stream().map(book -> book.getId()).collect(Collectors.toCollection(ArrayList::new)));
		}
		if (getYesOrNo("Create this publisher?"))
			return adminService.createPublisher(publisher);
		return operationCancelled;
	}

	private String updateAuthor() {
		ArrayList<Integer> selectedOptions;
		Author author = (Author) getObjectSelection("Which author would you like to update?",
				(ArrayList<LMSObject>) adminService.getAllObjects(LMS.author));
		ArrayList<LMSObject> addableBooks = ((ArrayList<LMSObject>) adminService.getAllObjects(LMS.book)).stream()
				.filter(book -> !author.getBookIds().contains(((Author) book).getId()))
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<String> options = PresUtils.newArrayList("Name");
		if (addableBooks.size() != 0)
			options.add("Add books");
		if (author.getBookIds().size() != 0)
			options.add("Remove books");
		selectedOptions = getMultiOptionSelection(secondUpdatePrompt(LMS.author), options);
		selectedOptions.stream().forEach(number -> {
			switch (options.get(number - 1)) {
			case "Name":
				author.setName(PresUtils.getStringWithMaxLength("What is the author's name?", "name",
						Presentation.maxStringFieldLength));
				break;
			case "Add books":
				ArrayList<Book> booksToAdd = (ArrayList<Book>) getMultiObjectSelection(
						"Which books has this author written?", addableBooks);
				author.setBookIds(
						booksToAdd.stream().map(book -> book.getId()).collect(Collectors.toCollection(ArrayList::new)));
				break;
			case "Remove books":
				ArrayList<Book> booksToRemove = (ArrayList<Book>) getMultiObjectSelection(
						"Which books hasn't this author written?",
						(ArrayList<LMSObject>) adminService.getObjectsById(LMS.book, author.getBookIds()));
				booksToRemove.stream().map(book -> author.getBookIds().remove(book.getId()));
				break;
			}
		});
		if (getYesOrNo("Update this author?"))
			return adminService.updateAuthor(author);
		return operationCancelled;
	}

	// need to add or remove books
	private String updateGenre() {
		ArrayList<Integer> selectedOptions;
		Genre genre = (Genre) getObjectSelection("Which genre would you like to update?",
				(ArrayList<LMSObject>) adminService.getAllObjects(LMS.genre));
		ArrayList<LMSObject> addableBooks = ((ArrayList<LMSObject>) adminService.getAllObjects(LMS.book)).stream()
				.filter(book -> !genre.getBookIds().contains(((Genre) book).getId()))
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<String> options = PresUtils.newArrayList("Name");
		if (addableBooks.size() != 0)
			options.add("Add books");
		if (genre.getBookIds().size() != 0)
			options.add("Remove books");
		selectedOptions = getMultiOptionSelection(secondUpdatePrompt(LMS.genre), options);
		selectedOptions.stream().forEach(number -> {
			switch (options.get(number - 1)) {
			case "Name":
				genre.setName(PresUtils.getStringWithMaxLength("What is the genre's name?", "name",
						Presentation.maxStringFieldLength));
				break;
			case "Add books":
				ArrayList<Book> booksToAdd = (ArrayList<Book>) getMultiObjectSelection(
						"Which books does this genre include?", addableBooks);
				genre.setBookIds(
						booksToAdd.stream().map(book -> book.getId()).collect(Collectors.toCollection(ArrayList::new)));
				break;
			case "Remove books":
				ArrayList<Book> booksToRemove = (ArrayList<Book>) getMultiObjectSelection(
						"Which books doesn't this genre include?",
						(ArrayList<LMSObject>) adminService.getObjectsById(LMS.book, genre.getBookIds()));
				booksToRemove.stream().map(book -> genre.getBookIds().remove(book.getId()));
				break;
			}
		});
		if (getYesOrNo("Update this genre?"))
			return adminService.updateGenre(genre);
		return operationCancelled;
	}

	// need to add or remove books
	private String updatePublisher() {
		Publisher publisher = (Publisher) getObjectSelection("Which publisher would you like to update?",
				(ArrayList<LMSObject>) adminService.getAllObjects(LMS.publisher));
		ArrayList<String> options = PresUtils.newArrayList("Name", "Associated books");
		ArrayList<Integer> selectedNumbers = getMultiOptionSelection(secondUpdatePrompt(LMS.publisher), options);
		selectedNumbers.stream().forEach(number -> {
			switch (options.get(number - 1)) {
			case "Name":
				publisher.setName(PresUtils.getStringWithMaxLength("What is the publisher's name?", "name",
						Presentation.maxStringFieldLength));
				break;
			case "Associated books":
				ArrayList<LMSObject> allBooks = (ArrayList<LMSObject>) adminService.getAllObjects(LMS.book);
				ArrayList<Book> books = (ArrayList<Book>) getMultiObjectSelection(
						"Which books has this publisher published?", allBooks);
				publisher.setBookIds(
						books.stream().map(book -> book.getId()).collect(Collectors.toCollection(ArrayList::new)));
				break;
			}
		});
		if (getYesOrNo("Update this publisher?"))
			return adminService.updatePublisher(publisher);
		return operationCancelled;
	}

	private String deleteAuthor() {
		Author author = (Author) PresUtils.getLMSObjectSelection(
				(List<LMSObject>) adminService.getAllObjects(LMS.author), deletePrompt(LMS.author),
				Presentation.cancelOperation);
		if (author == null)
			return operationCancelled;
		return adminService.deleteAuthor(author);
	}

	private String deleteBook() {
		Book book = (Book) PresUtils.getLMSObjectSelection((List<LMSObject>) adminService.getAllObjects(LMS.book),
				deletePrompt(LMS.book), Presentation.cancelOperation);
		if (book == null)
			return operationCancelled;
		return adminService.deleteBook(book);
	}

	private String deleteBorrower() {
		Borrower borrower = (Borrower) PresUtils.getLMSObjectSelection(
				(List<LMSObject>) adminService.getAllObjects(LMS.borrower), deletePrompt(LMS.borrower),
				Presentation.cancelOperation);
		if (borrower == null)
			return operationCancelled;
		return adminService.deleteBorrower(borrower);
	}

	private String deleteBranch() {
		Branch branch = (Branch) PresUtils.getLMSObjectSelection(
				(List<LMSObject>) adminService.getAllObjects(LMS.branch), deletePrompt(LMS.branch),
				Presentation.cancelOperation);
		if (branch == null)
			return operationCancelled;
		return adminService.deleteBranch(branch);
	}

	private String deleteGenre() {
		Genre genre = (Genre) PresUtils.getLMSObjectSelection((List<LMSObject>) adminService.getAllObjects(LMS.genre),
				deletePrompt(LMS.genre), Presentation.cancelOperation);
		if (genre == null)
			return operationCancelled;
		return adminService.deleteGenre(genre);
	}

	private String deletePublisher() {
		Publisher publisher = (Publisher) PresUtils.getLMSObjectSelection(
				(List<LMSObject>) adminService.getAllObjects(LMS.publisher), deletePrompt(LMS.publisher),
				Presentation.cancelOperation);
		if (publisher == null)
			return operationCancelled;
		return adminService.deletePublisher(publisher);
	}

	protected String crudRouter(String operation, String objectType) {
		switch (operation) {
		case Presentation.create:
			switch (objectType) {
			case LMS.author:
				return createAuthor();
			case LMS.book:
				return createBook();
			case LMS.borrower:
				return createBorrower();
			case LMS.branch:
				return createBranch();
			case LMS.genre:
				return createGenre();
			case LMS.publisher:
				return createPublisher();
			}
		case Presentation.read:
			switch (objectType) {
			case LMS.author:
				return adminService.readAuthors();
			case LMS.book:
				return adminService.readBooks();
			case LMS.borrower:
				return adminService.readBorrowers();
			case LMS.branch:
				return adminService.readBranches();
			case LMS.genre:
				return adminService.readGenres();
			case LMS.publisher:
				return adminService.readPublishers();
			}
		case Presentation.update:
			switch (objectType) {
			case LMS.author:
				return updateAuthor();
			case LMS.book:
				return updateBook();
			case LMS.borrower:
				return updateBorrower();
			case LMS.branch:
				return updateBranch();
			case LMS.genre:
				return updateGenre();
			case LMS.publisher:
				return updatePublisher();
			}
		case Presentation.delete:
			switch (objectType) {
			case LMS.author:
				return deleteAuthor();
			case LMS.book:
				return deleteBook();
			case LMS.borrower:
				return deleteBorrower();
			case LMS.branch:
				return deleteBranch();
			case LMS.genre:
				return deleteGenre();
			case LMS.publisher:
				return deletePublisher();
			}
		}
		return "No action was taken.";
	}

}

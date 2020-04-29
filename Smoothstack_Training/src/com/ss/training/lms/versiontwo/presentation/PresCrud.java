package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.AdminService;
import com.ss.training.lms.versiontwo.object.Author;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Genre;
import com.ss.training.lms.versiontwo.object.LMSObject;
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

	private boolean getYesOrNo(String prompt) {
		if (PresUtils.getOptionSelection(prompt, PresUtils.newArrayList("No", "Yes")) == 1)
			return true;
		return false;
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
				selectedOptions.add(selectionNumber);
			}
			return selectedOptions;
		}
	}

	private ArrayList<LMSObject> getMultiObjectSelection(String prompt, ArrayList<LMSObject> objects) {
		ArrayList<LMSObject> selectedObjects = new ArrayList<LMSObject>();
		ArrayList<String> options = objects.stream().map(object -> object.getDisplayName())
				.collect(Collectors.toCollection(ArrayList::new));
		ArrayList<Integer> selectedNumbers = getMultiOptionSelection(
				prompt + "\nEnter all that apply, separated by spaces.", options);
		selectedNumbers.stream().forEach(number -> selectedObjects.add(objects.get(number - 1)));
		return selectedObjects;
	}

	protected String createAuthor() {
		Author author = new Author();
		ArrayList<Book> allBooks = (ArrayList<Book>) adminService.getAllObjects(LMS.book);
		author.setName(PresUtils.getStringWithMaxLength("What is the author's name?", "name",
				Presentation.maxStringFieldLength));
		if(allBooks.size()!=0&&getYesOrNo("Has this author written any of the books in our system?")) {
			books = getMultiObjectSelection(adminService.getAllObjects(LMS.book);
			author.setBookIds(books.stream().map(book -> book.getId()).collect(Collectors.toCollection(ArrayList::new)));
		}
		if(getYesOrNo("Create this author?"))
			return adminService.createAuthor(author);
		return operationCancelled;
	}

	protected String create() {
		return adminService.create()
	}

	protected String create() {
		return adminService.create()
	}

	protected String create() {
		return adminService.create()
	}

	protected String create() {
		return adminService.create()
	}

	protected String create() {
		return adminService.create()
	}

	protected String deleteAuthor() {
		Author author = (Author) PresUtils.getLMSObjectSelection(
				(List<LMSObject>) adminService.getAllObjects(LMS.author), deletePrompt(LMS.author),
				Presentation.cancelOperation);
		if (author == null)
			return operationCancelled;
		return adminService.deleteAuthor(author);
	}

	protected String deleteBook() {
		Book book = (Book) PresUtils.getLMSObjectSelection((List<LMSObject>) adminService.getAllObjects(LMS.book),
				deletePrompt(LMS.book), Presentation.cancelOperation);
		if (book == null)
			return operationCancelled;
		return adminService.deleteBook(book);
	}

	protected String deleteBorrower() {
		Borrower borrower = (Borrower) PresUtils.getLMSObjectSelection(
				(List<LMSObject>) adminService.getAllObjects(LMS.borrower), deletePrompt(LMS.borrower),
				Presentation.cancelOperation);
		if (borrower == null)
			return operationCancelled;
		return adminService.deleteBorrower(borrower);
	}

	protected String deleteBranch() {
		Branch branch = (Branch) PresUtils.getLMSObjectSelection(
				(List<LMSObject>) adminService.getAllObjects(LMS.branch), deletePrompt(LMS.branch),
				Presentation.cancelOperation);
		if (branch == null)
			return operationCancelled;
		return adminService.deleteBranch(branch);
	}

	protected String deleteGenre() {
		Genre genre = (Genre) PresUtils.getLMSObjectSelection((List<LMSObject>) adminService.getAllObjects(LMS.genre),
				deletePrompt(LMS.genre), Presentation.cancelOperation);
		if (genre == null)
			return operationCancelled;
		return adminService.deleteGenre(genre);
	}

	protected String deletePublisher() {
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

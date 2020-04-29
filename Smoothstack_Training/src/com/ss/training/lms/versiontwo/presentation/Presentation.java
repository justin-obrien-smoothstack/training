package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.AdminService;
import com.ss.training.lms.versiontwo.business.BorrowerService;
import com.ss.training.lms.versiontwo.business.LibrarianService;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;

/**
 * Presentation tier of LMS application
 * 
 * @author Justin O'Brien
 */
public class Presentation {

	public static final int maxStringFieldLength = 45;
	/**
	 * Text shown to the user in menus
	 */
	public static final String exit = "Exit", librarian = "Librarian", admin = "Administrator", borrower = "Borrower",
			rootMenuPrompt = "Welcome to the library management system. Please indicate what type of user you are.",
			operationFailed = "The operation did not succeed.", operationSucceeded = "The operation was successful.";
	protected static final String cancelCode = "0";
	protected static final String genericPrompt = "What would you like to do?",
			cardPrompt = "Enter your library card number, or enter " + cancelCode + " to go back.",
			manageBranchPrompt = "Which branch do you manage?",
			updateBranchNamePrompt = "What is the branch's new name? Enter a blank line if it hasn't changed.",
			updateBranchAddressPrompt = "What is the branch's new address? Enter a blank line if it hasn't changed.",
			changeCopiesBookPrompt = "Which book would you like to change the number of copies of?",
			changeCopiesNumberPrompt = "What is the new number of copies of the book?",
			checkoutBranchPrompt = "Which branch would you like to check out a book from?",
			checkoutBookPrompt = "Which book would you like to check out?",
			returnBranchPrompt = "Which branch would you like to return a book to?",
			returnBookPrompt = "Which book would you like to return?",
			overridePrompt = "Which loan would you like to override the due date of?",
			adminCardPrompt = "Enter the borrower's card number, or enter " + cancelCode + " to go back.";
	protected static final String goBack = "Return to the previous menu", nevermind = "Nevermind",
			manageBranch = "Manage your branch", crud = "Create/Read/Update/Delete ", crudBooks = crud + "books",
			crudAuthors = crud + "authors", crudGenres = crud + "genres", crudPublishers = crud + "publishers",
			crudBranches = crud + "library branches", crudBorrowers = crud + "borrowers",
			override = "Override due date for a book loan", checkoutBook = "Check out a book",
			returnBook = "Return a book", updateBranch = "Update branch information",
			changeCopies = "Change the number of copies of a book at your branch", create = "Create", read = "Read",
			update = "Update", delete = "Delete", cancelOperation = "Cancel the operation";
	protected static String noChangesMade = "No changes have been made.";
	protected static final String invalidSelection = "Error: That is not a valid selection.",
			invalidCard = "Error: That is not a valid card number.",
			invalidCopies = "Error: That is not a valid number of copies.";

	protected static final Scanner scanner = new Scanner(System.in);

	private final LibrarianService librarianService = new LibrarianService();
	private final BorrowerService borrowerService = new BorrowerService();
	private final AdminService adminService = new AdminService();

	/**
	 * Removes all elements in a list of objects and repopulates the list
	 * 
	 * @param list        The list to be reset
	 * @param newElements The new contents to populate the list
	 */
	protected void resetList(List<Object> list, Object... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	/**
	 * Creates a prompt for when the user wants to do CRUD operations
	 * 
	 * @param objectType The type of object to be operated on
	 * @return The prompt
	 */
	protected String getCRUDPrompt(String objectType) {
		return "What operation do you want to do with " + objectType + "?";
	}

	/**
	 * Presents a menu to the user and takes action according to user input
	 * 
	 * @param prompt     The prompt shown to the user
	 * @param options    The options provided to the user
	 * @param parameters Anything else the application may need to execute
	 *                   functionality available through the menu to be presented
	 */
	public void presentMenu(String prompt, List<String> options, List<Object> parameters) {
		int selectedOption, cardNumber, branchPk, bookPk;
		String objectType;
		Object[][] bookPksTitlesAndAuthors;
		ArrayList<Integer> bookPks = new ArrayList<Integer>();
		Book currentBook;
		Branch currentBranch;
		Borrower currentBorrower;
		for (;;) {
			selectedOption = PresUtils.getOptionSelection(prompt, options);
			switch (options.get(selectedOption)) {
			case exit:
			case goBack:
			case nevermind:
				return;
			case librarian:
				presentMenu(genericPrompt, PresUtils.newArrayList(goBack, manageBranch), parameters);
				break;
			case borrower:
				currentBorrower = PresUtils.getBorrowerByCardNumber(cardPrompt);
				if (currentBorrower == null)
					continue;
				resetList(parameters, currentBorrower);
				presentMenu(genericPrompt, PresUtils.newArrayList(goBack, checkoutBook, returnBook), parameters);
				break;
			case admin:
				presentMenu(genericPrompt, PresUtils.newArrayList(goBack, crudBooks, crudAuthors, crudGenres,
						crudPublishers, crudBranches, crudBorrowers, override), parameters);
				break;
			case manageBranch:
				currentBranch = (Branch) PresUtils.getLMSObjectSelection(
						(List<LMSObject>) librarianService.getAllObjects(LMS.branch), manageBranchPrompt, goBack);
				if (currentBranch == null)
					return;
				resetList(parameters, currentBranch);
				presentMenu(genericPrompt, PresUtils.newArrayList(goBack, updateBranch, changeCopies), parameters);
				break;
			case updateBranch:
				final String noChange = "", newName, newAddress;
				currentBranch = (Branch) parameters.get(0);
				System.out.println(PresUtils.getBranchUpdateInfo(currentBranch, cancelCode));
				newName = PresUtils.getStringWithMaxLength(updateBranchNamePrompt, "name", maxStringFieldLength);
				if (cancelCode.equals(newName))
					continue;
				if (!noChange.equals(newName))
					currentBranch.setName(newName);
				newAddress = PresUtils.getStringWithMaxLength(updateBranchAddressPrompt, "address",
						maxStringFieldLength);
				if (cancelCode.equals(newAddress))
					continue;
				if (!noChange.equals(newAddress))
					currentBranch.setAddress(newAddress);
				System.out.println(librarianService.updateBranch(currentBranch));
				continue;
			case changeCopies:
				int newNumberOfCopies, currentNumberOfCopies = 0;
				currentBranch = (Branch) parameters.get(0);
				Book bookToChangeCopies = (Book) PresUtils.getLMSObjectSelection(
						(List<LMSObject>) librarianService.getAllObjects(LMS.book), changeCopiesBookPrompt, goBack);
				if (bookToChangeCopies == null)
					continue;
				for (Copies copies : currentBranch.getCopies())
					if (copies.getBookId() == bookToChangeCopies.getId()) {
						currentNumberOfCopies = copies.getCopies();
						break;
					}
				newNumberOfCopies = PresUtils
						.getNaturalNumber(PresUtils.changeCopiesNumberPrompt(currentNumberOfCopies), invalidCopies);
				if (newNumberOfCopies == currentNumberOfCopies) {
					System.out.println(noChangesMade);
					continue;
				}
				System.out.println(librarianService.updateCopies(currentBranch, bookToChangeCopies, newNumberOfCopies));
				continue;
			case checkoutBook:
				currentBorrower = (Borrower) parameters.get(0);
				currentBranch = (Branch) PresUtils.getLMSObjectSelection(borrowerService.getBranchesWithBooks(),
						checkoutBranchPrompt, goBack);
				if (currentBranch == null)
					continue;
				currentBook = (Book) PresUtils.getLMSObjectSelection(borrowerService.getAvailableBooks(currentBranch),
						checkoutBookPrompt, nevermind);
				if (currentBook == null)
					continue;
				System.out.println(borrowerService.checkoutBook(currentBorrower, currentBranch, currentBook));
				continue;
			case returnBook:
				currentBorrower = (Borrower) parameters.get(0);
				currentBranch = (Branch) PresUtils.getLMSObjectSelection(
						borrowerService.getBranchesWithLoans(currentBorrower), checkoutBranchPrompt, goBack);
				if (currentBranch == null)
					continue;
				currentBook = (Book) PresUtils.getLMSObjectSelection(
						borrowerService.getReturnableBooks(currentBorrower, currentBranch), returnBookPrompt,
						nevermind);
				if (currentBook == null)
					continue;
				System.out.println(borrowerService.returnBook(currentBorrower, currentBranch, currentBook));
				continue;
			case crudBooks:
				resetList(parameters, LMS.book);
				presentMenu(getCRUDPrompt(LMS.books), PresUtils.newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case crudAuthors:
				resetList(parameters, LMS.author);
				presentMenu(getCRUDPrompt(LMS.authors), PresUtils.newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case crudGenres:
				resetList(parameters, LMS.genre);
				presentMenu(getCRUDPrompt(LMS.genres), PresUtils.newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case crudPublishers:
				resetList(parameters, LMS.publisher);
				presentMenu(getCRUDPrompt(LMS.publishers), PresUtils.newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case crudBranches:
				resetList(parameters, LMS.branch);
				presentMenu(getCRUDPrompt(LMS.branches), PresUtils.newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case crudBorrowers:
				resetList(parameters, LMS.borrower);
				presentMenu(getCRUDPrompt(LMS.borrowers), PresUtils.newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case create:
				objectType = (String) parameters.get(0);
				System.out.println(new PresCrud().crudRouter(create, objectType));
				break;
			case read:
				objectType = (String) parameters.get(0);
				System.out.println(new PresCrud().crudRouter(read, objectType));
				break;
			case update:
				objectType = (String) parameters.get(0);
				System.out.println(new PresCrud().crudRouter(update, objectType));
				break;
			case delete:
				objectType = (String) parameters.get(0);
				System.out.println(new PresCrud().crudRouter(delete, objectType));
				break;
			case override:
				Loan loanToOverride;
				ArrayList<Loan> allBorrowerLoans;
				currentBorrower = (Borrower) PresUtils.getBorrowerByCardNumber(adminCardPrompt);
				allBorrowerLoans = (ArrayList<Loan>) currentBorrower.getLoans().clone();
				currentBorrower.setLoans(currentBorrower.getLoans().stream()
						.filter(loan -> loan.getDateIn() == null || loan.getDateIn().isAfter(loan.getDueDate()))
						.collect(Collectors.toCollection(ArrayList::new)));
				if (currentBorrower.getLoans().size() == 0) {
					System.out.println("This borrower has no loans with overridable due dates.");
					continue;
				}
				currentBranch = (Branch) PresUtils.getLMSObjectSelection(
						adminService.getBranchesWithLoans(currentBorrower), checkoutBranchPrompt, goBack);
				if (currentBranch == null)
					continue;
				loanToOverride = (Loan) PresUtils.getLMSObjectSelection(currentBorrower.getLoans(), overridePrompt,
						cancelOperation);
				if (loanToOverride == null)
					continue;
				currentBorrower.setLoans(allBorrowerLoans);
				System.out.println(adminService.overrideDueDate(currentBorrower, loanToOverride));
				continue;
			}
		}
	}

}
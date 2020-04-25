package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.ss.training.lms.versiontwo.business.Business;

/**
 * Presentation tier of LMS application
 * 
 * @author Justin O'Brien
 */
public class Presentation {

	/**
	 * The single instance of this class
	 */
	static Presentation instance = null;

	/**
	 * Private constructor to make this class a singleton
	 */
	private Presentation() {

	}

	/**
	 * Gets the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static Presentation getInstance() {
		if (instance == null)
			instance = new Presentation();
		return instance;
	}

	/**
	 * Text shown to the user in menus
	 */
	public final String exit = "Exit", librarian = "Librarian", admin = "Administrator", borrower = "Borrower",
			rootMenuPrompt = "Welcome to the library management system. Please indicate what type of user you are.";;
	private final String genericPrompt = "What would you like to do?",
			cardPrompt = "Please enter your library card number, or enter 0 to go back.",
			manageBranchPrompt = "Which branch do you manage?",
			updateBranchNamePrompt = "What is the branch's new name? Enter a blank line if it hasn't changed.",
			updateBranchAddressPrompt = "What is the branch's new address? Enter a blank line if it hasn't changed.",
			changeCopiesPrompt = "Which book would you like to change the number of copies of?",
			checkoutBranchPrompt = "Which branch would you like to check out a book from?",
			checkoutBookPrompt = "Which book would you like to check out?",
			returnBranchPrompt = "Which branch would you like to return a book to?",
			returnBookPrompt = "Which book would you like to return?";
	private final String goBack = "Return to the previous menu", manageBranch = "Manage your branch",
			crud = "Create/Read/Update/Delete ", crudBooks = crud + "books", crudAuthors = crud + "authors",
			crudGenres = crud + "genres", crudPublishers = crud + "publishers",
			crudBranches = crud + "library branches", crudBorrowers = crud + "borrowers",
			override = "Override due date for a book loan", checkoutBook = "Check out a book",
			returnBook = "Return a book", librarianUpdateBranch = "Update branch information",
			changeCopies = "Change the number of copies of a book at your branch";

	private final Scanner scanner = new Scanner(System.in);
	Business business = Business.getInstance();

	/**
	 * Prints numbered options available from a menu
	 * 
	 * @param options The options to be printed
	 */
	private void printOptions(List<String> options) {

	}

	/**
	 * Gets a menu option selection from the user
	 * 
	 * @param prompt  The prompt to show the user
	 * @param options The options the user is to select from
	 * @return The number corresponding to the option the user selects
	 */
	private int getOptionSelection(String prompt, List<String> options) {
		return 0; // placeholder
	}

	/**
	 * Removes all elements in a list of strings and repopulates the list
	 * 
	 * @param list        The list to be reset
	 * @param newElements The new contents to populate the list
	 */
	private void resetList(List<String> list, String... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	/**
	 * Removes all elements in a list of integers and repopulates the list
	 * 
	 * @param list        The list to be reset
	 * @param newElements The new contents to populate the list
	 */
	private void resetList(List<Integer> list, Integer... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	/**
	 * Removes all elements in a list of objects and repopulates the list
	 * 
	 * @param list        The list to be reset
	 * @param newElements The new contents to populate the list
	 */
	private void resetList(List<Object> list, Object... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	/**
	 * Prepares to have an element from one list selected based on the user's
	 * selection of an option from another list
	 * 
	 * @param userOptions      The list that will contain the options to be
	 *                         presented to the user
	 * @param crossOptions     The list that will contain the elements that can be
	 *                         selected based on the user's choice, as well as 0 for
	 *                         the option to return to the previous menu
	 * @param userOptionArray  The options to be presented to the user, other than
	 *                         returning to the previous menu
	 * @param crossOptionArray The elements that can be selected based on the user's
	 *                         choice
	 */
	private void prepareForCrossSelection(List<String> userOptions, List<Integer> crossOptions,
			String[] userOptionArray, Integer[] crossOptionArray) {
		resetList(userOptions, userOptionArray);
		resetList(crossOptions, crossOptionArray);
		userOptions.add(0, goBack);
		crossOptions.add(0, 0);
	}

	/**
	 * Gets a selection from the user and chooses a corresponding but different
	 * entity
	 * 
	 * @param prompt       The prompt to show the user
	 * @param userOptions  The options presented to the user
	 * @param crossOptions The elements that can be selected based on the user's
	 *                     choice
	 * @return The element corresponding to the option chosen by the user
	 */
	private int getCrossSelection(String prompt, List<String> userOptions, List<Integer> crossOptions) {
		return 0; // placeholder
	}

	/**
	 * Gets a library branch selection from the user
	 * 
	 * @param prompt The prompt to show the user
	 * @return Primary key of the selected branch, or 0 if the user wants to go back
	 *         to the previous menu
	 */
	private int getBranchSelection(String prompt) {
		int selectedOption;
		ArrayList<String> options = new ArrayList<String>();
		List<Integer> branchPks = new ArrayList<Integer>();
		Object[][] branchPksAndNames = business.getBranchPksAndNames();
		options.add(goBack);
		branchPks.add(0);
		options.addAll(Arrays.asList((String[]) branchPksAndNames[1]));
		branchPks.addAll(Arrays.asList((Integer[]) branchPksAndNames[0]));
		selectedOption = getOptionSelection(prompt, options);
		return branchPks.get(selectedOption);
	}

	/**
	 * Gets the user's card number
	 * 
	 * @return the user's card number, or 0 if the user wants to go back to the
	 *         previous menu
	 */
	private int getCardNumber() {
		return 0; // placeholder
	}

	private void printBranchUpdateInfo(int branchPk) {
		final String branchName = business.getBranchName(branchPk);
		System.out.println("Updating branch: " + branchName + " (#+" + branchPk
				+ ")\nEnter 0 at any prompt to cancel the operation.");
	}

	/**
	 * Gets the new number of copies of a book at a branch from the user
	 * 
	 * @param branchPk The primary key of the branch
	 * @param bookPk   The primary key of the book
	 * @return The new number of copies
	 */
	private int getNewNumberOfCopies(int branchPk, int bookPk) {
		return 0; // placeholder
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
		Object[][] bookPksTitlesAndAuthors;
		ArrayList<Integer> bookPks = new ArrayList<Integer>();
		for (;;) {
			selectedOption = getOptionSelection(prompt, options);
			switch (options.get(selectedOption)) {
			case exit:
			case goBack:
				return;
			case librarian:
				resetList(options, goBack, manageBranch);
				presentMenu(genericPrompt, options, parameters);
				break;
			case borrower:
				cardNumber = getCardNumber();
				if (cardNumber == 0)
					return;
				resetList(parameters, cardNumber);
				resetList(options, goBack, checkoutBook, returnBook);
				presentMenu(genericPrompt, options, parameters);
				break;
			case admin:
				resetList(options, goBack, crudBooks, crudAuthors, crudGenres, crudPublishers, crudBranches,
						crudBorrowers, override);
				presentMenu(genericPrompt, options, parameters);
				break;
			case manageBranch:
				branchPk = getBranchSelection(manageBranchPrompt);
				if (branchPk == 0)
					return;
				resetList(options, librarianUpdateBranch, changeCopies);
				resetList(parameters, branchPk);
				presentMenu(genericPrompt, options, parameters);
				break;
			case librarianUpdateBranch:
				final String cancelOperation = "0", noChange = "", newName, newAddress;
				branchPk = (Integer) parameters.get(0);
				printBranchUpdateInfo(branchPk);
				System.out.println(updateBranchNamePrompt);
				newName = scanner.nextLine();
				if (cancelOperation.equals(newName))
					return;
				System.out.println(updateBranchAddressPrompt);
				newAddress = scanner.nextLine();
				if (cancelOperation.equals(newAddress))
					return;
				System.out.println(business.librarianUpdateBranch(newName, newAddress, noChange));
				return;
			case changeCopies:
				int newNumberOfCopies;
				branchPk = (Integer) parameters.get(0);
				bookPksTitlesAndAuthors = business.getAllBookPksTitlesAndAuthors();
				prepareForCrossSelection(options, bookPks, (String[]) bookPksTitlesAndAuthors[1],
						(Integer[]) bookPksTitlesAndAuthors[0]);
				bookPk = getCrossSelection(changeCopiesPrompt, options, bookPks);
				if (bookPk == 0)
					return;
				newNumberOfCopies = getNewNumberOfCopies(branchPk, bookPk);
				System.out.println(business.updateNumberOfCopies(branchPk, bookPk, newNumberOfCopies));
				return;
			case checkoutBook:
				cardNumber = (Integer) parameters.get(0);
				branchPk = getBranchSelection(checkoutBranchPrompt);
				if (branchPk == 0)
					return;
				bookPksTitlesAndAuthors = business.getAvailableBookPksTitlesAndAuthors(branchPk);
				prepareForCrossSelection(options, bookPks, (String[]) bookPksTitlesAndAuthors[1],
						(Integer[]) bookPksTitlesAndAuthors[0]);
				bookPk = getCrossSelection(checkoutBookPrompt, options, bookPks);
				if (bookPk == 0)
					return;
				System.out.println(business.checkoutBook(cardNumber, branchPk, bookPk));
				return;
			case returnBook:
				cardNumber = (Integer) parameters.get(0);
				branchPk = getBranchSelection(returnBranchPrompt);
				if (branchPk == 0)
					return;
				bookPksTitlesAndAuthors = business.getReturnableBookPksTitlesAndAuthors(cardNumber, branchPk);
				prepareForCrossSelection(options, bookPks, (String[]) bookPksTitlesAndAuthors[1],
						(Integer[]) bookPksTitlesAndAuthors[0]);
				bookPk = getCrossSelection(returnBookPrompt, options, bookPks);
				if (bookPk == 0)
					return;
				System.out.println(business.returnBook(cardNumber, branchPk, bookPk));
				return;
			}
		}
	}

}

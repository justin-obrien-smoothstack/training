package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.Business;

/**
 * Presentation tier of LMS application
 * 
 * @author Justin O'Brien
 */
public class Presentation {

	/**
	 * Text shown to the user in menus
	 */
	public static final String exit = "Exit", librarian = "Librarian", admin = "Administrator", borrower = "Borrower";
	private static final String genericPrompt = "What would you like to do?",
			cardPrompt = "Please enter your library card number, or enter 0 to go back.",
			manageBranchPrompt = "Which branch do you manage?",
			checkoutBranchPrompt = "Which branch would you like to check out a book from?",
			checkoutBookPrompt = "Which book would you like to check out?",
			returnBranchPrompt = "Which branch would you like to return a book to?",
			returnBookPrompt = "Which book would you like to return?";
	private static final String goBack = "Return to the previous menu", manageBranch = "Manage your branch",
			crud = "Create/Read/Update/Delete ", crudBooks = crud + "books", crudAuthors = crud + "authors",
			crudGenres = crud + "genres", crudPublishers = crud + "publishers",
			crudBranches = crud + "library branches", crudBorrowers = crud + "borrowers",
			override = "Override due date for a book loan", checkoutBook = "Check out a book",
			returnBook = "Return a book", updateBranch = "Update branch information",
			addCopies = "Add copies of a book to your branch";

	/**
	 * Prints numbered options available from a menu
	 * 
	 * @param options The options to be printed
	 */
	private static void printOptions(List<String> options) {

	}

	/**
	 * Gets a menu option selection from the user
	 * 
	 * @param prompt  The prompt to show the user
	 * @param options The options the user is to select from
	 * @return The number corresponding to the option the user selects
	 */
	private static int getOptionSelection(String prompt, List<String> options) {
		return 0; // placeholder
	}

	private static void resetList(List<String> list, String... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	private static void resetList(List<Object> list, Object... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	/**
	 * Gets the user's card number
	 * 
	 * @return the user's card number, or 0 if the user wants to go back to the
	 *         previous menu
	 */
	private static int getCardNumber() {
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
	public static void presentMenu(String prompt, List<String> options, List<Object> parameters) {
		int optionSelection, cardNumber, branchPk, bookPk;
		ArrayList<Integer> branchPks;
		for (;;) {
			switch (options.get(getOptionSelection(prompt, options))) {
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
				options = Business.getDbColumnAsStrings(LMS.branchTable, LMS.branchPkColumn);
				branchPks = Business.getDbColumnAsIntegers(LMS.branchTable, LMS.branchPkColumn);
				options.add(0, goBack);
				branchPks.add(0, null);
				optionSelection = getOptionSelection(manageBranchPrompt, options);
				if (optionSelection == 0)
					return;
				branchPk = branchPks.get(optionSelection);
				resetList(options, updateBranch, addCopies);
				resetList(parameters, branchPk);
				presentMenu(genericPrompt, options, parameters);
				break;
			case updateBranch:
				branchPk = (Integer) parameters.get(0);
				
				break;
			case checkoutBook:
				ArrayList<Integer> availableBookPks = new ArrayList<Integer>();
				cardNumber = (Integer) parameters.get(0);
				options = Business.getDbColumnAsStrings(LMS.branchTable, LMS.branchPkColumn);
				branchPks = Business.getDbColumnAsIntegers(LMS.branchTable, LMS.branchPkColumn);
				options.add(0, goBack);
				branchPks.add(0, null);
				optionSelection = getOptionSelection(checkoutBranchPrompt, options);
				if (optionSelection == 0)
					return;
				branchPk = branchPks.get(optionSelection);
				options = Business.getAvailableBookTitlesAndAuthors(branchPk);
				availableBookPks = Business.getAvailableBookPks(branchPk);
				options.add(0, goBack);
				availableBookPks.add(0, null);
				optionSelection = getOptionSelection(checkoutBookPrompt, options);
				if (optionSelection == 0)
					return;
				bookPk = availableBookPks.get(optionSelection);
				System.out.println(Business.checkoutBook(cardNumber, branchPk, bookPk));
				break;
			case returnBook:
				ArrayList<Integer> returnableBookPks = new ArrayList<Integer>();
				cardNumber = (Integer) parameters.get(0);
				options = Business.getDbColumnAsStrings(LMS.branchTable, LMS.branchPkColumn);
				branchPks = Business.getDbColumnAsIntegers(LMS.branchTable, LMS.branchPkColumn);
				options.add(0, goBack);
				branchPks.add(0, null);
				optionSelection = getOptionSelection(returnBranchPrompt, options);
				if (optionSelection == 0)
					return;
				branchPk = branchPks.get(optionSelection);
				options = Business.getReturnableBookTitlesAndAuthors(cardNumber, branchPk);
				returnableBookPks = Business.getReturnableBookPks(cardNumber, branchPk);
				options.add(0, goBack);
				returnableBookPks.add(0, null);
				optionSelection = getOptionSelection(returnBookPrompt, options);
				if (optionSelection == 0)
					return;
				bookPk = returnableBookPks.get(optionSelection);
				System.out.println(Business.checkoutBook(cardNumber, branchPk, bookPk));
				break;
			}
		}
	}
}

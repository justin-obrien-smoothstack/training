package com.ss.training.lms.versiontwo.presentation;

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
			checkoutBookPrompt = "Which book would you like to check out?";
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
	public static void printOptions(String[] options) {

	}

	/**
	 * Gets a menu option selection from the user
	 * 
	 * @param prompt  The prompt to show the user
	 * @param options The options the user is to select from
	 * @return The number corresponding to the option the user selects
	 */
	public static int getOptionSelection(String prompt, String[] options) {
		return 0; // placeholder
	}

	/**
	 * Gets the user's card number
	 * 
	 * @return the user's card number, or 0 if the user wants to go back to the
	 *         previous menu
	 */
	public static int getCardNumber() {
		return 0; // placeholder
	}

	/**
	 * Presentation tier of LMS application
	 * 
	 * @param prompt     The prompt shown to the user
	 * @param options    The options provided to the user
	 * @param parameters Anything else the application may need to execute
	 *                   functionality available through the menu to be presented
	 */
	public static void presentMenu(String prompt, String[] options, Object[] parameters) {
		int cardNumber, branchPk, bookPk;
		Integer[] branchPks;
		for (;;) {
			System.out.println(prompt);
			switch (options[getOptionSelection(prompt, options)]) {
			case exit:
			case goBack:
				return;
			case librarian:
				options = new String[] { goBack, manageBranch };
				presentMenu(genericPrompt, options, parameters);
				break;
			case borrower:
				cardNumber = getCardNumber();
				if (cardNumber == 0)
					return;
				parameters = new Integer[] { cardNumber };
				options = new String[] { goBack, checkoutBook, returnBook };
				presentMenu(genericPrompt, options, parameters);
				break;
			case admin:
				options = new String[] { goBack, crudBooks, crudAuthors, crudGenres, crudPublishers, crudBranches,
						crudBorrowers, override };
				presentMenu(genericPrompt, options, parameters);
				break;
			case manageBranch:
				branchPks = (Integer[]) Business.getDbColumnAsArray(LMS.branchTable, LMS.branchPkColumn);
				options = (String[]) Business.getDbColumnAsArray(LMS.branchTable, LMS.branchPkColumn);
				branchPk = branchPks[getOptionSelection(manageBranchPrompt, options)]; // need to allow return
				parameters = new Integer[] { branchPk };
				options = new String[] { updateBranch, addCopies };
				presentMenu(genericPrompt, options, parameters);
				break;
			case checkoutBook:
				cardNumber = (Integer) parameters[0];
				Integer[] availableBookPks;
				Object[][] availableBooks;
				branchPks = (Integer[]) Business.getDbColumnAsArray(LMS.branchTable, LMS.branchPkColumn);
				options = (String[]) Business.getDbColumnAsArray(LMS.branchTable, LMS.branchPkColumn);
				branchPk = branchPks[getOptionSelection(checkoutBranchPrompt, options)]; // need to allow return
				availableBooks = Business.getAvailableBooks(branchPk);
				availableBookPks = (Integer[]) availableBooks[0];
				options = (String[]) availableBooks[1];
				bookPk = availableBookPks[getOptionSelection(checkoutBookPrompt, options)]; // need to allow return
				System.out.println(Business.checkoutBook(cardNumber, branchPk, bookPk));
				break;
			case updateBranch:
				branchPk = (Integer) parameters[0];
				// add more here
				break;
			}
		}
	}
}

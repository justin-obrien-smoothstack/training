package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.AdminService;
import com.ss.training.lms.versiontwo.business.BorrowerService;
import com.ss.training.lms.versiontwo.business.LibrarianService;
import com.ss.training.lms.versiontwo.objects.LMSObject;

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
			rootMenuPrompt = "Welcome to the library management system. Please indicate what type of user you are.";
	private final String cancelCode = "0";
	private final String genericPrompt = "What would you like to do?",
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
			overridePrompt = "Which loan would you like to override the due date of?";
	private final String goBack = "Return to the previous menu", manageBranch = "Manage your branch",
			crud = "Create/Read/Update/Delete ", crudBooks = crud + "books", crudAuthors = crud + "authors",
			crudGenres = crud + "genres", crudPublishers = crud + "publishers",
			crudBranches = crud + "library branches", crudBorrowers = crud + "borrowers",
			override = "Override due date for a book loan", checkoutBook = "Check out a book",
			returnBook = "Return a book", updateBranch = "Update branch information",
			changeCopies = "Change the number of copies of a book at your branch", create = "Create", read = "Read",
			update = "Update", delete = "Delete", cancelOperation = "Cancel the operation";
	private final String invalidSelection = "Error: That is not a valid selection.",
			invalidCard = "Error: That is not a valid card number.",
			invalidCopies = "Error: That is not a valid number of copies.";

	private final Scanner scanner = new Scanner(System.in);

	private final LibrarianService librarianService = LibrarianService.getInstance();
	private final BorrowerService borrowerService = BorrowerService.getInstance();
	private final AdminService adminService = AdminService.getInstance();

	/**
	 * Gets a menu option selection from the user
	 * 
	 * @param prompt  The prompt to show the user
	 * @param options The options the user is to select from
	 * @return The number corresponding to the option the user selects
	 */
	private int getOptionSelection(String prompt, List<String> options) {
		int i, selectionNumber;
		for (;;) {
			System.out.println(prompt);
			for (i = 0; i < options.size(); i++) {
				System.out.println(i + ") " + options.get(i));
			}
			try {
				selectionNumber = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(invalidSelection);
				continue;
			}
			if (0 <= selectionNumber && selectionNumber <= options.size())
				return selectionNumber;
			System.out.println(invalidSelection);
		}
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
	 * Prepares to select an integer based on the user's selection of string
	 * 
	 * @param userOptions      The list that will contain the options to be
	 *                         presented to the user
	 * @param crossOptions     The list that will contain the integers that can be
	 *                         selected based on the user's choice
	 * @param userOptionArray  The options to be presented to the user, other than
	 *                         returning to the previous menu
	 * @param crossOptionArray The integers that can be selected based on the user's
	 *                         choice, other than that corresponding to returning to
	 *                         the previous menu
	 */
	private void prepareForIntCrossSelection(List<String> userOptions, List<Integer> crossOptions,
			String[] userOptionArray, Integer[] crossOptionArray) {
		resetList(userOptions, userOptionArray);
		resetList(crossOptions, crossOptionArray);
		userOptions.add(0, goBack);
		crossOptions.add(0, 0);
	}

	/**
	 * Prepares to select an integer based on the user's selection of string
	 * 
	 * @param userOptions      The list that will contain the options to be
	 *                         presented to the user
	 * @param crossOptions     The list that will contain the integers that can be
	 *                         selected based on the user's choice
	 * @param userOptionArray  The options to be presented to the user, other than
	 *                         returning to the previous menu
	 * @param crossOptionArray The integers that can be selected based on the user's
	 *                         choice, other than that corresponding to returning to
	 *                         the previous menu
	 */
	private void prepareForLoanCrossSelection(List<String> userOptions, List<Loan> crossOptions,
			String[] userOptionArray, Loan[] crossOptionArray) {
		resetList(userOptions, userOptionArray);
		resetList(crossOptions, crossOptionArray);
		userOptions.add(0, goBack);
		crossOptions.add(0, null);
	}

	/**
	 * Gets a selection from the user and chooses a corresponding integer
	 * 
	 * @param prompt       The prompt to show the user
	 * @param userOptions  The options presented to the user
	 * @param crossOptions The integers that can be selected based on the user's
	 *                     choice
	 * @return The integer corresponding to the option chosen by the user
	 */
	private int getIntCrossSelection(String prompt, List<String> userOptions, List<Integer> crossOptions) {
		return crossOptions.get(getOptionSelection(prompt, userOptions));
	}

	/**
	 * Gets a selection from the user and chooses a corresponding book loan
	 * 
	 * @param prompt       The prompt to show the user
	 * @param userOptions  The options presented to the user
	 * @param crossOptions The book loans that can be selected based on the user's
	 *                     choice
	 * @return The book loan corresponding to the option chosen by the user
	 */
	private Loan getLoanCrossSelection(String prompt, List<String> userOptions, List<Loan> crossOptions) {
		return crossOptions.get(getOptionSelection(prompt, userOptions));
	}

	/**
	 * Gets a library branch selection from the user
	 * 
	 * @param prompt The prompt to show the user
	 * @return Primary key of the selected branch, or 0 if the user wants to go back
	 *         to the previous menu
	 */
	private int getBranchSelection(String prompt) {
		ArrayList<String> options = new ArrayList<String>();
		ArrayList<Integer> branchPks = new ArrayList<Integer>();
		Object[][] branchPksAndNames = librarianService.getBranchPksAndNames();
		prepareForIntCrossSelection(options, branchPks, (String[]) branchPksAndNames[1],
				(Integer[]) branchPksAndNames[0]);
		return getIntCrossSelection(prompt, options, branchPks);
	}

	/**
	 * Gets the user's card number
	 * 
	 * @param prompt The prompt to show the user
	 * @return The card number input by the user, or 0 if the user wants to go back
	 *         to the previous menu
	 */
	private int getCardNumber(String prompt) {
		int cardNumber;
		List<Integer> cardNumbers = borrowerService.getCardNumbers();
		for (;;) {
			System.out.println(prompt);
			try {
				cardNumber = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(invalidCard);
				continue;
			}
			if (cardNumbers.contains(cardNumber))
				return cardNumber;
			System.out.println(invalidCard);
		}
	}

	/**
	 * @param branchPk The primary key of the branch being updated
	 * 
	 * @return String stating which branch is being updated and telling the user how
	 *         to cancel the update
	 */
	private String branchUpdateInfo(int branchPk) {
		final String branchName = librarianService.getBranchName(branchPk);
		return "Updating branch: " + branchName + " (#+" + branchPk + ")\nEnter " + cancelCode
				+ " at any prompt to cancel the operation.";
	}

	/**
	 * Gets the new number of copies of a book at a branch from the user
	 * 
	 * @param branchPk The primary key of the branch
	 * @param bookPk   The primary key of the book
	 * @return The new number of copies
	 */
	private int getNewNumberOfCopies(String prompt) {
		int NewNumberOfCopies;
		for (;;) {
			System.out.println(prompt);
			try {
				NewNumberOfCopies = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(invalidCopies);
				continue;
			}
			if (NewNumberOfCopies >= 0)
				return NewNumberOfCopies;
			System.out.println(invalidCopies);
		}
	}

	/**
	 * Creates a prompt for when the user wants to do CRUD operations
	 * 
	 * @param objectType The type of object to be operated on
	 * @return The prompt
	 */
	private String getCRUDPrompt(String objectType) {
		return "What operation do you want to do with " + objectType + "?";
	}

	/**
	 * Creates a prompt for a user to enter a value for a single-string-valued field
	 * of an object.
	 * 
	 * @param objectType The type of object
	 * @param fieldName  The name of the field
	 * @return The prompt
	 */
	private String getMonoFieldPrompt(String objectType, String fieldName) {
		return "What is the " + fieldName + " of the " + objectType + "? Enter a blank line to cancel the operation.";
	}

	/**
	 * Creates a prompt for a user to select values for a multi-valued field of an
	 * object.
	 * 
	 * @param objectType The type of object
	 * @param fieldName  The name of the field
	 * @return The prompt
	 */
	private String getMultiFieldPrompt(String objectType, String fieldName) {
		return "What are the " + fieldName + " of the " + objectType + "? To add multiple " + fieldName
				+ ", enter the numbers on a single line, separated with spaces. Enter a blank line to cancel the operation.";
	}

	/**
	 * Gets a prompt asking the user whether to set an optional field of an object
	 * 
	 * @param fieldName The name of the optional field
	 * @return The prompt
	 */
	private String addFieldPrompt(String fieldName) {
		return "Add " + fieldName + "?";
	}

	/**
	 * Creates an object in the database
	 * 
	 * @param objectType The type of object to create
	 * @return A string indicating whether the operation succeeded
	 */
	private String createObject(String objectType) {
		int selectedOption;
		String fieldValue;
		LMSObject newObject = adminService.getBlankObject(objectType);
		ArrayList<Integer> selectedOptions;
		ArrayList<String> options,
				yesOrNo = (ArrayList<String>) Arrays.asList(new String[] { cancelOperation, "Yes", "No" });
		ArrayList<LMSObject> possiblyRelatedObjects = new ArrayList<LMSObject>();
		HashMap<String, HashMap<String, HashMap<String, Object>>> newObjectFields = newObject.getFieldsMap();
		HashMap<String, Object> independentRequired = newObjectFields.get(LMS.independent).get(LMS.required);
		HashMap<String, Object> independentOptional = newObjectFields.get(LMS.independent).get(LMS.optional);
		HashMap<String, Object> relationalMono = (HashMap<String, Object>) newObjectFields.get(LMS.relational)
				.get(LMS.mono);
		HashMap<String, Object> relationalMulti = (HashMap<String, Object>) newObjectFields.get(LMS.relational)
				.get(LMS.multi);
		for (String fieldName : independentRequired.keySet()) {
			System.out.println(getMonoFieldPrompt(objectType, fieldName));
			fieldValue = scanner.nextLine();
			if (fieldValue.isEmpty())
				return "";
			independentRequired.put(fieldName, fieldValue);
		}
		for (String fieldName : independentOptional.keySet()) {
			selectedOption = getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
			if (selectedOption == 0)
				return "";
			if (selectedOption == 1) {
				System.out.println(getMonoFieldPrompt(objectType, fieldName));
				fieldValue = scanner.nextLine();
				if (fieldValue.isEmpty())
					return "";
				independentOptional.put(fieldName, fieldValue);
			}
		}
		for (String fieldName : relationalMono.keySet()) {
			selectedOption = getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
			if (selectedOption == 0)
				return "";
			if (selectedOption == 1) {
				possiblyRelatedObjects = adminService.getAllObjects(fieldName);
				options = possiblyRelatedObjects.stream().map(object -> object.getName())
						.collect(Collectors.toCollection(ArrayList::new));
				options.add(0, cancelOperation);
				possiblyRelatedObjects.add(0, null);
				selectedOption = getOptionSelection(getMonoFieldPrompt(objectType, fieldName), options);
				if (selectedOption == 0)
					return "";
				relationalMono.put(fieldName, possiblyRelatedObjects.get(selectedOption));
			}
		}
		for (String fieldName : relationalMulti.keySet()) {
			selectedOption = getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
			if (selectedOption == 0)
				return "";
			if (selectedOption == 1) {
				possiblyRelatedObjects = adminService.getAllObjects(fieldName);
				options = possiblyRelatedObjects.stream().map(object -> object.getName())
						.collect(Collectors.toCollection(ArrayList::new));
				options.add(0, cancelOperation);
				possiblyRelatedObjects.add(0, null);
				selectedOptions = getMultiOptionSelection(getMultiFieldPrompt(objectType, fieldName), options);
				if (selectedOptions.contains(0))
					return "";
				selectedOptions.stream().forEach(option -> ((ArrayList<LMSObject>) relationalMulti.get(fieldName))
						.add(possiblyRelatedObjects.get(option)));
			}
		}
		newObject.setFieldsMap(newObjectFields);
		return adminService.create(newObject);
	}

	/**
	 * Gets information on all objects of a given type in the database
	 * 
	 * @param objectType The type of object to read
	 * @return A string with information on all objects of the given type
	 */
	private String readObjects(String objectType) {
		return null; // placeholder
	}

	/**
	 * Updates an object in the database
	 * 
	 * @param objectType The type of object to update
	 * @return A string indicating whether the operation succeeded
	 */
	private String updateObject(String objectType) {
		return null; // placeholder
	}

	/**
	 * Deletes an object in the database
	 * 
	 * @param objectType The type of object to delete
	 * @return A string indicating whether the operation succeeded
	 */
	private String deleteObject(String objectType) {
		return null; // placeholder
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
				cardNumber = getCardNumber(cardPrompt);
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
				resetList(options, updateBranch, changeCopies);
				resetList(parameters, branchPk);
				presentMenu(genericPrompt, options, parameters);
				break;
			case updateBranch:
				final String cancelOperation = "0", noChange = "", newName, newAddress;
				branchPk = (Integer) parameters.get(0);
				System.out.println(branchUpdateInfo(branchPk));
				System.out.println(updateBranchNamePrompt);
				newName = scanner.nextLine();
				if (cancelOperation.equals(newName))
					return;
				System.out.println(updateBranchAddressPrompt);
				newAddress = scanner.nextLine();
				if (cancelOperation.equals(newAddress))
					return;
				System.out.println(librarianService.updateBranch(newName, newAddress, noChange));
				return;
			case changeCopies:
				int newNumberOfCopies;
				branchPk = (Integer) parameters.get(0);
				bookPksTitlesAndAuthors = librarianService.getAllBookPksTitlesAndAuthors();
				prepareForIntCrossSelection(options, bookPks, (String[]) bookPksTitlesAndAuthors[1],
						(Integer[]) bookPksTitlesAndAuthors[0]);
				bookPk = getIntCrossSelection(changeCopiesBookPrompt, options, bookPks);
				if (bookPk == 0)
					return;
				newNumberOfCopies = getNewNumberOfCopies(changeCopiesNumberPrompt);
				System.out.println(librarianService.updateNumberOfCopies(branchPk, bookPk, newNumberOfCopies));
				return;
			case checkoutBook:
				cardNumber = (Integer) parameters.get(0);
				branchPk = getBranchSelection(checkoutBranchPrompt);
				if (branchPk == 0)
					return;
				bookPksTitlesAndAuthors = borrowerService.getAvailableBookPksTitlesAndAuthors(branchPk);
				prepareForIntCrossSelection(options, bookPks, (String[]) bookPksTitlesAndAuthors[1],
						(Integer[]) bookPksTitlesAndAuthors[0]);
				bookPk = getIntCrossSelection(checkoutBookPrompt, options, bookPks);
				if (bookPk == 0)
					return;
				System.out.println(borrowerService.checkoutBook(cardNumber, branchPk, bookPk));
				return;
			case returnBook:
				cardNumber = (Integer) parameters.get(0);
				branchPk = getBranchSelection(returnBranchPrompt);
				if (branchPk == 0)
					return;
				bookPksTitlesAndAuthors = borrowerService.getReturnableBookPksTitlesAndAuthors(cardNumber, branchPk);
				prepareForIntCrossSelection(options, bookPks, (String[]) bookPksTitlesAndAuthors[1],
						(Integer[]) bookPksTitlesAndAuthors[0]);
				bookPk = getIntCrossSelection(returnBookPrompt, options, bookPks);
				if (bookPk == 0)
					return;
				System.out.println(borrowerService.returnBook(cardNumber, branchPk, bookPk));
				return;
			case crudBooks:
				resetList(parameters, LMS.book);
				resetList(options, goBack, create, read, update, delete);
				presentMenu(getCRUDPrompt(LMS.books), options, parameters);
				break;
			case crudAuthors:
				resetList(parameters, LMS.author);
				resetList(options, goBack, create, read, update, delete);
				presentMenu(getCRUDPrompt(LMS.authors), options, parameters);
				break;
			case crudGenres:
				resetList(parameters, LMS.genre);
				resetList(options, goBack, create, read, update, delete);
				presentMenu(getCRUDPrompt(LMS.genres), options, parameters);
				break;
			case crudPublishers:
				resetList(parameters, LMS.publisher);
				resetList(options, goBack, create, read, update, delete);
				presentMenu(getCRUDPrompt(LMS.publishers), options, parameters);
				break;
			case crudBranches:
				resetList(parameters, LMS.branch);
				resetList(options, goBack, create, read, update, delete);
				presentMenu(getCRUDPrompt(LMS.branches), options, parameters);
				break;
			case crudBorrowers:
				resetList(parameters, LMS.borrower);
				resetList(options, goBack, create, read, update, delete);
				presentMenu(getCRUDPrompt(LMS.borrowers), options, parameters);
				break;
			case create:
				objectType = (String) parameters.get(0);
				System.out.println(createObject(objectType));
				break;
			case read:
				objectType = (String) parameters.get(0);
				System.out.println(readObjects(objectType));
				return;
			case update:
				objectType = (String) parameters.get(0);
				System.out.println(updateObject(objectType));
				break;
			case delete:
				objectType = (String) parameters.get(0);
				System.out.println(deleteObject(objectType));
				break;
			case override:
				Object[][] loansAndDescriptions = adminService.getOverridableLoansAndDescriptions();
				Loan loanToOverride;
				ArrayList<Loan> overridableLoans = new ArrayList<Loan>();
				prepareForLoanCrossSelection(options, overridableLoans, (String[]) loansAndDescriptions[1],
						(Loan[]) loansAndDescriptions[0]);
				loanToOverride = getLoanCrossSelection(overridePrompt, options, overridableLoans);
				System.out.println(adminService.overrideDueDate(loanToOverride));
				break;
			}
		}
	}

}

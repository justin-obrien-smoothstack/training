package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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

	private final int maxStringFieldLength = 45;
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
			overridePrompt = "Which loan would you like to override the due date of?";
	protected static final String goBack = "Return to the previous menu", manageBranch = "Manage your branch",
			crud = "Create/Read/Update/Delete ", crudBooks = crud + "books", crudAuthors = crud + "authors",
			crudGenres = crud + "genres", crudPublishers = crud + "publishers",
			crudBranches = crud + "library branches", crudBorrowers = crud + "borrowers",
			override = "Override due date for a book loan", checkoutBook = "Check out a book",
			returnBook = "Return a book", updateBranch = "Update branch information",
			changeCopies = "Change the number of copies of a book at your branch", create = "Create", read = "Read",
			update = "Update", delete = "Delete", cancelOperation = "Cancel the operation";
	protected static final String operationCancelled = "The operation was cancelled.",
			noChangesMade = "No changes have been made.";
	protected static final String invalidSelection = "Error: That is not a valid selection.",
			invalidCard = "Error: That is not a valid card number.",
			invalidCopies = "Error: That is not a valid number of copies.";

	protected static final Scanner scanner = new Scanner(System.in);

	protected final LibrarianService librarianService = new LibrarianService();
	protected final BorrowerService borrowerService = new BorrowerService();
	protected final AdminService adminService = new AdminService();

	/**
	 * Removes all elements in a list of strings and repopulates the list
	 * 
	 * @param list        The list to be reset
	 * @param newElements The new contents to populate the list
	 */
	protected void resetList(List<String> list, String... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	/**
	 * Removes all elements in a list of integers and repopulates the list
	 * 
	 * @param list        The list to be reset
	 * @param newElements The new contents to populate the list
	 */
	protected void resetList(List<Integer> list, Integer... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

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

	protected void resetList(List<Loan> list, Loan... newElements) {
		list.clear();
		Collections.addAll(list, newElements);
	}

	protected ArrayList<String> newArrayList(String... elements) {
		ArrayList<String> result = new ArrayList<String>();
		Collections.addAll(result, elements);
		return result;
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
	protected void prepareForIntCrossSelection(List<String> userOptions, List<Integer> crossOptions,
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
	protected void prepareForLoanCrossSelection(List<String> userOptions, List<Loan> crossOptions,
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
	protected int getIntCrossSelection(String prompt, List<String> userOptions, List<Integer> crossOptions) {
		return crossOptions.get(PresUtils.getOptionSelection(prompt, userOptions));
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
	protected Loan getLoanCrossSelection(String prompt, List<String> userOptions, List<Loan> crossOptions) {
		return crossOptions.get(PresUtils.getOptionSelection(prompt, userOptions));
	}

	/**
	 * Gets a library branch selection from the user
	 * 
	 * @param prompt The prompt to show the user
	 * @return Primary key of the selected branch, or 0 if the user wants to go back
	 *         to the previous menu
	 */
	protected int getBranchSelection(String prompt) {
		ArrayList<String> options = new ArrayList<String>();
		ArrayList<Integer> branchPks = new ArrayList<Integer>();
		Object[][] branchPksAndNames = librarianService.getBranchPksAndNames();
		prepareForIntCrossSelection(options, branchPks, (String[]) branchPksAndNames[1],
				(Integer[]) branchPksAndNames[0]);
		return getIntCrossSelection(prompt, options, branchPks);
	}

	/**
	 * Gets the new number of copies of a book at a branch from the user
	 * 
	 * @param branchPk The primary key of the branch
	 * @param bookPk   The primary key of the book
	 * @return The new number of copies
	 */
	protected int getNewNumberOfCopies(String prompt) {
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

	protected String[] getChangeOrRemoveOpts(String subject) {
		return new String[] { cancelCode, "Change " + subject, "Remove " + subject };
	}

	protected String[] getAddOrRemoveOpts(String subject) {
		return new String[] { cancelCode, "Add " + subject, "Remove " + subject };
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

	protected String getObjectUpdatePrompt(String objectType) {
		return "Which " + objectType + "do you want to update?";
	}

	protected String getObjectDeletionPrompt(String objectType) {
		return "Which " + objectType + "do you want to delete?";
	}

	protected String getFieldUpdatePrompt(String objectType) {
		return "What information about this " + objectType + "do you want to update?";
	}

	/**
	 * Creates a prompt for a user to enter a value for a single-string-valued field
	 * of an object.
	 * 
	 * @param objectType The type of object
	 * @param fieldName  The name of the field
	 * @return The prompt
	 */
	protected String getMonoStringFieldPrompt(String objectType, String fieldName) {
		return "What is the " + fieldName + " of the " + objectType + "? Enter a blank line to cancel the operation.";
	}

	/**
	 * Creates a prompt for a user to enter a value for a single-object-valued field
	 * of an object.
	 * 
	 * @param objectType The type of object whose field is to be set
	 * @param fieldName  The name of the field
	 * @return The prompt
	 */
	protected String getMonoObjectFieldPrompt(String objectType, String fieldName) {
		return "What is the " + fieldName + " of the " + objectType + "?";
	}

	/**
	 * Creates a prompt for a user to select values for a multi-valued field of an
	 * object.
	 * 
	 * @param objectType The type of object
	 * @param fieldName  The name of the field
	 * @return The prompt
	 */
	protected String getMultiFieldPrompt(String objectType, String fieldName) {
		return "What are the " + fieldName + " of the " + objectType + "? To add multiple " + fieldName
				+ ", enter the numbers on a single line, separated with spaces.";
	}

	/**
	 * Creates a prompt for a user to select values to add for a multi-valued field
	 * of an object.
	 * 
	 * @param fieldName The name of the field
	 * @return The prompt
	 */
	protected String getAddMultiFieldPrompt(String fieldName) {
		return "What " + fieldName + " should be added? To add multiple " + fieldName
				+ ", enter the numbers on a single line, separated with spaces.";
	}

	/**
	 * Creates a prompt for a user to remove values for a multi-valued field of an
	 * object.
	 * 
	 * @param fieldName The name of the field
	 * @return The prompt
	 */
	protected String getRemoveMultiFieldPrompt(String fieldName) {
		return "What " + fieldName + " should be removed? To add multiple " + fieldName
				+ ", enter the numbers on a single line, separated with spaces.";
	}

	/**
	 * Gets a prompt asking the user whether to set an optional field of an object
	 * 
	 * @param fieldName The name of the optional field
	 * @return The prompt
	 */
	protected String addFieldPrompt(String fieldName) {
		return "Add " + fieldName + "?";
	}

	protected ArrayList<Integer> getMultiOptionSelection(String prompt, ArrayList<String> options) {
		int i, selectionNumber;
		String[] userInput;
		ArrayList<Integer> selectedOptions = new ArrayList<Integer>();
		outerLoop: for (;;) {
			System.out.println(prompt);
			for (i = 0; i < options.size(); i++) {
				System.out.println(i + ") " + options.get(i));
			}
			userInput = scanner.nextLine().split(" ");
			for (String selectedOption : userInput) {
				try {
					selectionNumber = Integer.parseInt(selectedOption);
				} catch (NumberFormatException e) {
					System.out.println(invalidSelection);
					selectedOptions.clear();
					continue outerLoop;
				}
				if (selectionNumber < 0 || selectionNumber >= options.size()) {
					System.out.println(invalidSelection);
					selectedOptions.clear();
					continue outerLoop;
				}
				selectedOptions.add(selectionNumber);
			}
			return selectedOptions;
		}
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
				return;
			case librarian:
				presentMenu(genericPrompt, newArrayList(goBack, manageBranch), parameters);
				break;
			case borrower:
				currentBorrower = PresUtils.getBorrowerByCardNumber(cardPrompt);
				if (currentBorrower == null)
					continue;
				resetList(parameters, currentBorrower);
				presentMenu(genericPrompt, newArrayList(goBack, checkoutBook, returnBook), parameters);
				break;
			case admin:
				presentMenu(genericPrompt, newArrayList(goBack, crudBooks, crudAuthors, crudGenres, crudPublishers,
						crudBranches, crudBorrowers, override), parameters);
				break;
			case manageBranch:
				currentBranch = (Branch) PresUtils.getLMSObjectSelection(
						(List<LMSObject>) librarianService.getAllObjects(LMS.branch), manageBranchPrompt, goBack);
				if (currentBranch == null)
					return;
				resetList(parameters, currentBranch);
				presentMenu(genericPrompt, newArrayList(goBack, updateBranch, changeCopies), parameters);
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
				System.out
						.println(librarianService.updateCopies(currentBranch, bookToChangeCopies, newNumberOfCopies));
				continue;
			case checkoutBook:
				currentBorrower = (Borrower) parameters.get(0);
				currentBranch = (Branch) PresUtils.getLMSObjectSelection(borrowerService.getBranchesWithBooks(),
						checkoutBranchPrompt, goBack);
				if(currentBranch == null)
					continue;
				currentBook = (Book) PresUtils.getLMSObjectSelection(borrowerService.getAvailableBooks(currentBranch),
						checkoutBookPrompt, goBack);
				if(currentBook == null)
					continue;
				System.out.println(borrowerService.checkoutBook(currentBorrower, currentBranch, currentBook));
				continue;
			case returnBook:
				cardNumber = (Integer) parameters.get(0);
				// must change to only show branches with active loans
				branchPk = getBranchSelection(returnBranchPrompt);
				if (branchPk == 0)
					continue;
				bookPksTitlesAndAuthors = borrowerService.getReturnableBookPksTitlesAndAuthors(cardNumber, branchPk);
				prepareForIntCrossSelection(options, bookPks, (String[]) bookPksTitlesAndAuthors[1],
						(Integer[]) bookPksTitlesAndAuthors[0]);
				bookPk = getIntCrossSelection(returnBookPrompt, options, bookPks);
				if (bookPk == 0)
					continue;
				System.out.println(borrowerService.returnBook(cardNumber, branchPk, bookPk));
				continue;
			case crudBooks:
				resetList(parameters, LMS.book);
				presentMenu(getCRUDPrompt(LMS.books), newArrayList(goBack, create, read, update, delete), parameters);
				break;
			case crudAuthors:
				resetList(parameters, LMS.author);
				presentMenu(getCRUDPrompt(LMS.authors), newArrayList(goBack, create, read, update, delete), parameters);
				break;
			case crudGenres:
				resetList(parameters, LMS.genre);
				presentMenu(getCRUDPrompt(LMS.genres), newArrayList(goBack, create, read, update, delete), parameters);
				break;
			case crudPublishers:
				resetList(parameters, LMS.publisher);
				presentMenu(getCRUDPrompt(LMS.publishers), newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case crudBranches:
				resetList(parameters, LMS.branch);
				presentMenu(getCRUDPrompt(LMS.branches), newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case crudBorrowers:
				resetList(parameters, LMS.borrower);
				presentMenu(getCRUDPrompt(LMS.borrowers), newArrayList(goBack, create, read, update, delete),
						parameters);
				break;
			case create:
				objectType = (String) parameters.get(0);
//				System.out.println(createObject(objectType));
				break;
			case read:
				objectType = (String) parameters.get(0);
//				System.out.println(readObjects(objectType));
				return;
			case update:
				objectType = (String) parameters.get(0);
//				System.out.println(updateObject(objectType));
				break;
			case delete:
				objectType = (String) parameters.get(0);
//				System.out.println(deleteObject(objectType));
				break;
			case override:
				// must change to take borrower ID & branch
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

//	/**
//	 * Creates an object in the database
//	 * 
//	 * @param objectType The type of object to create
//	 * @return A string indicating whether the operation succeeded
//	 */
//	protected static String createObject(String objectType) {
//		int selectedOption;
//		String fieldValue;
//		LMSObject newObject = adminService.getBlankObject(objectType);
//		ArrayList<Integer> selectedOptions;
//		ArrayList<String> options,
//				yesOrNo = (ArrayList<String>) Arrays.asList(new String[] { cancelOperation, "Yes", "No" });
//		ArrayList<LMSObject> possiblyRelatedObjects = new ArrayList<LMSObject>();
//		HashMap<String, HashMap<String, Object>> newObjectFields = newObject.getFieldsMap();
//		HashMap<String, Object> independentRequired = (HashMap<String, Object>) newObjectFields.get(LMS.independent)
//				.get(LMS.required);
//		HashMap<String, Object> independentOptional = (HashMap<String, Object>) newObjectFields.get(LMS.independent)
//				.get(LMS.optional);
//		HashMap<String, Object> relationalMono = (HashMap<String, Object>) newObjectFields.get(LMS.relational)
//				.get(LMS.mono);
//		HashMap<String, Object> relationalMulti = (HashMap<String, Object>) newObjectFields.get(LMS.relational)
//				.get(LMS.multi);
//		// HashMap<String, Object> composite = newObjectFields.get(LMS.composite);
//		for (String fieldName : independentRequired.keySet()) {
//			System.out.println(getMonoStringFieldPrompt(objectType, fieldName));
//			fieldValue = scanner.nextLine();
//			if (fieldValue.isEmpty())
//				return operationCancelled;
//			independentRequired.put(fieldName, fieldValue);
//		}
//		for (String fieldName : independentOptional.keySet()) {
//			selectedOption = PresUtils.getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
//			if (selectedOption == 0)
//				return operationCancelled;
//			if (selectedOption == 1) {
//				System.out.println(getMonoStringFieldPrompt(objectType, fieldName));
//				fieldValue = scanner.nextLine();
//				if (fieldValue.isEmpty())
//					return operationCancelled;
//				independentOptional.put(fieldName, fieldValue);
//			}
//		}
//		for (String fieldName : relationalMono.keySet()) {
//			selectedOption = PresUtils.getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
//			if (selectedOption == 0)
//				return operationCancelled;
//			if (selectedOption == 1) {
//				possiblyRelatedObjects.clear();
//				possiblyRelatedObjects.addAll(adminService.getAllObjects(fieldName));
//				options = possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
//						.collect(Collectors.toCollection(ArrayList::new));
//				options.add(0, cancelOperation);
//				possiblyRelatedObjects.add(0, null);
//				selectedOption = PresUtils.getOptionSelection(getMonoStringFieldPrompt(objectType, fieldName), options);
//				if (selectedOption == 0)
//					return operationCancelled;
//				relationalMono.put(fieldName, possiblyRelatedObjects.get(selectedOption));
//			}
//		}
//		for (String fieldName : relationalMulti.keySet()) {
//			selectedOption = PresUtils.getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
//			if (selectedOption == 0)
//				return operationCancelled;
//			if (selectedOption == 1) {
//				possiblyRelatedObjects.clear();
//				possiblyRelatedObjects.addAll(adminService.getAllObjects(fieldName));
//				options = possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
//						.collect(Collectors.toCollection(ArrayList::new));
//				options.add(0, cancelOperation);
//				possiblyRelatedObjects.add(0, null);
//				selectedOptions = getMultiOptionSelection(getMultiFieldPrompt(objectType, fieldName), options);
//				if (selectedOptions.contains(0))
//					return operationCancelled;
//				selectedOptions.stream().forEach(option -> ((ArrayList<LMSObject>) relationalMulti.get(fieldName))
//						.add(possiblyRelatedObjects.get(option)));
//			}
//		}
//		newObject.setFieldsMap(newObjectFields);
//		return adminService.create(newObject);
//	}
//
//	/**
//	 * Gets information on all objects of a given type in the database
//	 * 
//	 * @param objectType The type of object to read
//	 * @return A string with information on all objects of the given type
//	 */
//	protected static String readObjects(String objectType) {
//		StringBuilder result = new StringBuilder("\n");
//		ArrayList<LMSObject> objectsToRead = adminService.getAllObjects(objectType);
//		objectsToRead.stream().map(object -> object.getFieldsMap()).forEach(fieldsMap -> {
//			HashMap<String, Object> independentFieldsMap = fieldsMap.get(LMS.independent);
//			HashMap<String, Object> relationalFieldsMap = fieldsMap.get(LMS.relational);
//			HashMap<String, Object> relationalMonoFieldsMap = (HashMap<String, Object>) relationalFieldsMap
//					.get(LMS.mono);
//			HashMap<String, Object> relationalMultiFieldsMap = (HashMap<String, Object>) relationalFieldsMap
//					.get(LMS.multi);
//			independentFieldsMap.keySet().stream().forEach(independentFieldsSubmapName -> {
//				HashMap<String, Object> independentFieldsSubmap = (HashMap<String, Object>) independentFieldsMap
//						.get(independentFieldsSubmapName);
//				independentFieldsSubmap.keySet().stream().forEach(independentFieldName -> {
//					String independentFieldValue = independentFieldsSubmap.get(independentFieldName) == null ? ""
//							: (String) independentFieldsSubmap.get(independentFieldName);
//					result.append(independentFieldName + ": " + independentFieldValue);
//				});
//			});
//			relationalMonoFieldsMap.keySet().stream().forEach(relationalMonoFieldName -> {
//				LMSObject relationalMonoFieldValue = (LMSObject) relationalMonoFieldsMap.get(relationalMonoFieldName);
//				String relationalMonoFieldValueName = (relationalMonoFieldValue == null
//						|| relationalMonoFieldValue.getDisplayName() == null) ? ""
//								: relationalMonoFieldValue.getDisplayName();
//				result.append(relationalMonoFieldName + ": " + relationalMonoFieldValueName);
//			});
//			relationalMultiFieldsMap.keySet().stream().forEach(relationalMultiFieldName -> {
//				ArrayList<LMSObject> relationalMultiFieldValues = (ArrayList<LMSObject>) relationalMultiFieldsMap
//						.get(relationalMultiFieldName);
//				String relationalMultiFieldValueNames = relationalMultiFieldValues.stream()
//						.map(relationalMultiFieldValue -> relationalMultiFieldValue.getDisplayName())
//						.reduce((partialResult, nextItem) -> partialResult + ", " + nextItem).orElse("");
//				result.append(relationalMultiFieldName + ": " + relationalMultiFieldValueNames);
//			});
//			result.append("\n");
//		});
//		return result.toString();
//	}
//
//	/**
//	 * Updates an object in the database
//	 * 
//	 * @param objectType The type of object to update
//	 * @return A string indicating whether the operation succeeded
//	 */
//	protected static String updateObject(String objectType) {
//		int selectedOption;
//		ArrayList<Integer> selectedOptions;
//		String nameOfFieldToUpdate, newFieldValueString;
//		ArrayList<LMSObject> updateCandidates = adminService.getAllObjects(objectType), possiblyRelatedObjects,
//				valuesOfFieldToUpdate;
//		ArrayList<String> options = updateCandidates.stream().map(object -> object.getDisplayName())
//				.collect(Collectors.toCollection(ArrayList::new)), tempOptions = new ArrayList<String>();
//		HashMap<String, HashMap<String, Object>> fieldsMap;
//		HashMap<String, Object> independentRequired, independentOptional, relationalMono, relationalMulti;
//		LMSObject objectToUpdate, newFieldValueObject;
//		updateCandidates.add(0, null);
//		options.add(0, cancelOperation);
//		selectedOption = PresUtils.getOptionSelection(getObjectUpdatePrompt(objectType), options);
//		objectToUpdate = updateCandidates.get(selectedOption);
//		fieldsMap = objectToUpdate.getFieldsMap();
//		tempOptions.add(cancelOperation);
//		fieldsMap.keySet().stream().forEach(fieldsMapKey -> {
//			HashMap<String, Object> fieldsSubmap = fieldsMap.get(fieldsMapKey);
//			if (!LMS.composite.equals(fieldsMapKey)) {
//				fieldsSubmap.keySet().stream().forEach(fieldsSubmapKey -> {
//					HashMap<String, Object> fieldsSubsubmap = (HashMap<String, Object>) fieldsSubmap
//							.get(fieldsSubmapKey);
//					fieldsSubsubmap.keySet().stream().forEach(fieldName -> tempOptions.add(fieldName));
//				});
//			}
//		});
//		selectedOption = PresUtils.getOptionSelection(getFieldUpdatePrompt(objectType), options);
//		if (selectedOption == 0)
//			return operationCancelled;
//		nameOfFieldToUpdate = options.get(selectedOption);
//		independentRequired = (HashMap<String, Object>) fieldsMap.get(LMS.independent).get(LMS.required);
//		independentOptional = (HashMap<String, Object>) fieldsMap.get(LMS.independent).get(LMS.optional);
//		relationalMono = (HashMap<String, Object>) fieldsMap.get(LMS.relational).get(LMS.mono);
//		relationalMulti = (HashMap<String, Object>) fieldsMap.get(LMS.relational).get(LMS.multi);
//		if (independentRequired.keySet().contains(nameOfFieldToUpdate)) {
//			System.out.println(getMonoStringFieldPrompt(objectType, nameOfFieldToUpdate));
//			newFieldValueString = scanner.nextLine();
//			if (newFieldValueString.isEmpty())
//				return operationCancelled;
//			independentRequired.put(nameOfFieldToUpdate, newFieldValueString);
//			objectToUpdate.setFieldsMap(fieldsMap);
//			return adminService.update(objectToUpdate);
//		}
//		if (independentOptional.keySet().contains(nameOfFieldToUpdate)) {
//			if (independentOptional.get(nameOfFieldToUpdate) != null) {
//				options = newArrayList(getChangeOrRemoveOpts(nameOfFieldToUpdate));
//				selectedOption = PresUtils.getOptionSelection(genericPrompt, options);
//				if (selectedOption == 0)
//					return operationCancelled;
//				if (selectedOption == 2) {
//					independentOptional.put(nameOfFieldToUpdate, null);
//					objectToUpdate.setFieldsMap(fieldsMap);
//					return adminService.update(objectToUpdate);
//				}
//			}
//			System.out.println(getMonoStringFieldPrompt(objectType, nameOfFieldToUpdate));
//			newFieldValueString = scanner.nextLine();
//			if (newFieldValueString.isEmpty())
//				return operationCancelled;
//			independentOptional.put(nameOfFieldToUpdate, newFieldValueString);
//			objectToUpdate.setFieldsMap(fieldsMap);
//			return adminService.update(objectToUpdate);
//		}
//		if (relationalMono.keySet().contains(nameOfFieldToUpdate)) {
//			if (relationalMono.get(nameOfFieldToUpdate) != null) {
//				options = newArrayList(getChangeOrRemoveOpts(nameOfFieldToUpdate));
//				selectedOption = PresUtils.getOptionSelection(genericPrompt, options);
//				if (selectedOption == 0)
//					return operationCancelled;
//				if (selectedOption == 2) {
//					relationalMono.put(nameOfFieldToUpdate, null);
//					objectToUpdate.setFieldsMap(fieldsMap);
//					return adminService.update(objectToUpdate);
//				}
//			}
//			System.out.println(getMonoStringFieldPrompt(objectType, nameOfFieldToUpdate));
//			possiblyRelatedObjects = adminService.getAllObjects(nameOfFieldToUpdate);
//			possiblyRelatedObjects.add(0, null);
//			options.clear();
//			options.addAll(possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
//					.collect(Collectors.toCollection(ArrayList::new)));
//			selectedOption = PresUtils.getOptionSelection(getMonoObjectFieldPrompt(objectType, nameOfFieldToUpdate),
//					options);
//			if (selectedOption == 0)
//				return operationCancelled;
//			newFieldValueObject = possiblyRelatedObjects.get(selectedOption);
//			relationalMono.put(nameOfFieldToUpdate, newFieldValueObject);
//			objectToUpdate.setFieldsMap(fieldsMap);
//			return adminService.update(objectToUpdate);
//		}
//		if (relationalMulti.keySet().contains(nameOfFieldToUpdate)) {
//			valuesOfFieldToUpdate = (ArrayList<LMSObject>) relationalMulti.get(nameOfFieldToUpdate);
//			if (valuesOfFieldToUpdate.size() != 0) {
//				options = newArrayList(getAddOrRemoveOpts(nameOfFieldToUpdate));
//				selectedOption = PresUtils.getOptionSelection(genericPrompt, options);
//				if (selectedOption == 0)
//					return operationCancelled;
//				if (selectedOption == 2) {
//					options.clear();
//					options.addAll(valuesOfFieldToUpdate.stream().map(object -> object.getDisplayName())
//							.collect(Collectors.toCollection(ArrayList::new)));
//					options.add(0, cancelOperation);
//					valuesOfFieldToUpdate.add(0, null);
//					selectedOptions = getMultiOptionSelection(getRemoveMultiFieldPrompt(nameOfFieldToUpdate), options);
//					if (selectedOptions.contains(0))
//						return operationCancelled;
//					for (int selectionNumber : selectedOptions)
//						valuesOfFieldToUpdate.remove(selectionNumber);
//					valuesOfFieldToUpdate.remove(null);
//					objectToUpdate.setFieldsMap(fieldsMap);
//					return adminService.update(objectToUpdate);
//				}
//				possiblyRelatedObjects = adminService.getAllObjects(objectType);
//				for (LMSObject objectAlreadyPresent : valuesOfFieldToUpdate)
//					possiblyRelatedObjects.removeIf(object -> object.equals(objectAlreadyPresent));
//				options.clear();
//				options.addAll(possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
//						.collect(Collectors.toCollection(ArrayList::new)));
//				options.add(0, cancelOperation);
//				possiblyRelatedObjects.add(0, null);
//				selectedOptions = getMultiOptionSelection(getAddMultiFieldPrompt(nameOfFieldToUpdate), options);
//				if (selectedOptions.contains(0))
//					return operationCancelled;
//				selectedOptions.stream().forEach(index -> valuesOfFieldToUpdate.add(possiblyRelatedObjects.get(index)));
//				objectToUpdate.setFieldsMap(fieldsMap);
//				return adminService.update(objectToUpdate);
//			}
//		}
//		return "Oops, something went wrong.";
//	}
//
//	/**
//	 * Deletes an object in the database
//	 * 
//	 * @param objectType The type of object to delete
//	 * @return A string indicating whether the operation succeeded
//	 */
//	protected static String deleteObject(String objectType) {
//		int selectedOption;
//		LMSObject objectToDelete;
//		ArrayList<LMSObject> deletionCandidates = adminService.getAllObjects(objectType);
//		ArrayList<String> options = deletionCandidates.stream().map(object -> object.getDisplayName())
//				.collect(Collectors.toCollection(ArrayList::new));
//		options.add(0, cancelOperation);
//		deletionCandidates.add(null);
//		selectedOption = PresUtils.getOptionSelection(getObjectDeletionPrompt(objectType), options);
//		if (selectedOption == 0)
//			return operationCancelled;
//		objectToDelete = deletionCandidates.get(selectedOption);
//		return (adminService.delete(objectToDelete));
//	}
}

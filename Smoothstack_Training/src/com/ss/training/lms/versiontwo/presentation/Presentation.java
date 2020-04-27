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
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;

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
	private final String operationCancelled = "The operation was cancelled.";
	private final String invalidSelection = "Error: That is not a valid selection.",
			invalidCard = "Error: That is not a valid card number.",
			invalidCopies = "Error: That is not a valid number of copies.";

	private final Scanner scanner = new Scanner(System.in);

	private final LibrarianService librarianService = new LibrarianService();
	private final BorrowerService borrowerService = new BorrowerService();
	private final AdminService adminService = new AdminService();

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
			if (0 <= selectionNumber && selectionNumber < options.size())
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

	private void resetList(List<Loan> list, Loan... newElements) {
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

	private String[] getChangeOrRemoveOpts(String subject) {
		return new String[] { cancelOperation, "Change " + subject, "Remove " + subject };
	}

	private String[] getAddOrRemoveOpts(String subject) {
		return new String[] { cancelOperation, "Add " + subject, "Remove " + subject };
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

	private String getObjectUpdatePrompt(String objectType) {
		return "Which " + objectType + "do you want to update?";
	}

	private String getObjectDeletionPrompt(String objectType) {
		return "Which " + objectType + "do you want to delete?";
	}

	private String getFieldUpdatePrompt(String objectType) {
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
	private String getMonoStringFieldPrompt(String objectType, String fieldName) {
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
	private String getMonoObjectFieldPrompt(String objectType, String fieldName) {
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
	private String getMultiFieldPrompt(String objectType, String fieldName) {
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
	private String getAddMultiFieldPrompt(String fieldName) {
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
	private String getRemoveMultiFieldPrompt(String fieldName) {
		return "What " + fieldName + " should be removed? To add multiple " + fieldName
				+ ", enter the numbers on a single line, separated with spaces.";
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

	private ArrayList<Integer> getMultiOptionSelection(String prompt, ArrayList<String> options) {
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
		HashMap<String, HashMap<String, Object>> newObjectFields = newObject.getFieldsMap();
		HashMap<String, Object> independentRequired = (HashMap<String, Object>) newObjectFields.get(LMS.independent)
				.get(LMS.required);
		HashMap<String, Object> independentOptional = (HashMap<String, Object>) newObjectFields.get(LMS.independent)
				.get(LMS.optional);
		HashMap<String, Object> relationalMono = (HashMap<String, Object>) newObjectFields.get(LMS.relational)
				.get(LMS.mono);
		HashMap<String, Object> relationalMulti = (HashMap<String, Object>) newObjectFields.get(LMS.relational)
				.get(LMS.multi);
		// HashMap<String, Object> composite = newObjectFields.get(LMS.composite);
		for (String fieldName : independentRequired.keySet()) {
			System.out.println(getMonoStringFieldPrompt(objectType, fieldName));
			fieldValue = scanner.nextLine();
			if (fieldValue.isEmpty())
				return operationCancelled;
			independentRequired.put(fieldName, fieldValue);
		}
		for (String fieldName : independentOptional.keySet()) {
			selectedOption = getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
			if (selectedOption == 0)
				return operationCancelled;
			if (selectedOption == 1) {
				System.out.println(getMonoStringFieldPrompt(objectType, fieldName));
				fieldValue = scanner.nextLine();
				if (fieldValue.isEmpty())
					return operationCancelled;
				independentOptional.put(fieldName, fieldValue);
			}
		}
		for (String fieldName : relationalMono.keySet()) {
			selectedOption = getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
			if (selectedOption == 0)
				return operationCancelled;
			if (selectedOption == 1) {
				possiblyRelatedObjects.clear();
				possiblyRelatedObjects.addAll(adminService.getAllObjects(fieldName));
				options = possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
						.collect(Collectors.toCollection(ArrayList::new));
				options.add(0, cancelOperation);
				possiblyRelatedObjects.add(0, null);
				selectedOption = getOptionSelection(getMonoStringFieldPrompt(objectType, fieldName), options);
				if (selectedOption == 0)
					return operationCancelled;
				relationalMono.put(fieldName, possiblyRelatedObjects.get(selectedOption));
			}
		}
		for (String fieldName : relationalMulti.keySet()) {
			selectedOption = getOptionSelection(addFieldPrompt(fieldName), yesOrNo);
			if (selectedOption == 0)
				return operationCancelled;
			if (selectedOption == 1) {
				possiblyRelatedObjects.clear();
				possiblyRelatedObjects.addAll(adminService.getAllObjects(fieldName));
				options = possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
						.collect(Collectors.toCollection(ArrayList::new));
				options.add(0, cancelOperation);
				possiblyRelatedObjects.add(0, null);
				selectedOptions = getMultiOptionSelection(getMultiFieldPrompt(objectType, fieldName), options);
				if (selectedOptions.contains(0))
					return operationCancelled;
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
		StringBuilder result = new StringBuilder("\n");
		ArrayList<LMSObject> objectsToRead = adminService.getAllObjects(objectType);
		objectsToRead.stream().map(object -> object.getFieldsMap()).forEach(fieldsMap -> {
			HashMap<String, Object> independentFieldsMap = fieldsMap.get(LMS.independent);
			HashMap<String, Object> relationalFieldsMap = fieldsMap.get(LMS.relational);
			HashMap<String, Object> relationalMonoFieldsMap = (HashMap<String, Object>) relationalFieldsMap
					.get(LMS.mono);
			HashMap<String, Object> relationalMultiFieldsMap = (HashMap<String, Object>) relationalFieldsMap
					.get(LMS.multi);
			independentFieldsMap.keySet().stream().forEach(independentFieldsSubmapName -> {
				HashMap<String, Object> independentFieldsSubmap = (HashMap<String, Object>) independentFieldsMap
						.get(independentFieldsSubmapName);
				independentFieldsSubmap.keySet().stream().forEach(independentFieldName -> {
					String independentFieldValue = independentFieldsSubmap.get(independentFieldName) == null ? ""
							: (String) independentFieldsSubmap.get(independentFieldName);
					result.append(independentFieldName + ": " + independentFieldValue);
				});
			});
			relationalMonoFieldsMap.keySet().stream().forEach(relationalMonoFieldName -> {
				LMSObject relationalMonoFieldValue = (LMSObject) relationalMonoFieldsMap.get(relationalMonoFieldName);
				String relationalMonoFieldValueName = (relationalMonoFieldValue == null
						|| relationalMonoFieldValue.getDisplayName() == null) ? ""
								: relationalMonoFieldValue.getDisplayName();
				result.append(relationalMonoFieldName + ": " + relationalMonoFieldValueName);
			});
			relationalMultiFieldsMap.keySet().stream().forEach(relationalMultiFieldName -> {
				ArrayList<LMSObject> relationalMultiFieldValues = (ArrayList<LMSObject>) relationalMultiFieldsMap
						.get(relationalMultiFieldName);
				String relationalMultiFieldValueNames = relationalMultiFieldValues.stream()
						.map(relationalMultiFieldValue -> relationalMultiFieldValue.getDisplayName())
						.reduce((partialResult, nextItem) -> partialResult + ", " + nextItem).orElse("");
				result.append(relationalMultiFieldName + ": " + relationalMultiFieldValueNames);
			});
			result.append("\n");
		});
		return result.toString();
	}

	/**
	 * Updates an object in the database
	 * 
	 * @param objectType The type of object to update
	 * @return A string indicating whether the operation succeeded
	 */
	private String updateObject(String objectType) {
		int selectedOption;
		ArrayList<Integer> selectedOptions;
		String nameOfFieldToUpdate, newFieldValueString;
		ArrayList<LMSObject> updateCandidates = adminService.getAllObjects(objectType), possiblyRelatedObjects,
				valuesOfFieldToUpdate;
		ArrayList<String> options = updateCandidates.stream().map(object -> object.getDisplayName())
				.collect(Collectors.toCollection(ArrayList::new));
		HashMap<String, HashMap<String, Object>> fieldsMap;
		HashMap<String, Object> independentRequired, independentOptional, relationalMono, relationalMulti;
		LMSObject objectToUpdate, newFieldValueObject;
		updateCandidates.add(0, null);
		options.add(0, cancelOperation);
		selectedOption = getOptionSelection(getObjectUpdatePrompt(objectType), options);
		objectToUpdate = updateCandidates.get(selectedOption);
		fieldsMap = objectToUpdate.getFieldsMap();
		options.clear();
		options.add(0, cancelOperation);
		fieldsMap.keySet().stream().forEach(fieldsMapKey -> {
			HashMap<String, Object> fieldsSubmap = fieldsMap.get(fieldsMapKey);
			if (!LMS.composite.equals(fieldsMapKey)) {
				fieldsSubmap.keySet().stream().forEach(fieldsSubmapKey -> {
					HashMap<String, Object> fieldsSubsubmap = (HashMap<String, Object>) fieldsSubmap
							.get(fieldsSubmapKey);
					fieldsSubsubmap.keySet().stream().forEach(fieldName -> options.add(fieldName));
				});
			}
		});
		selectedOption = getOptionSelection(getFieldUpdatePrompt(objectType), options);
		if (selectedOption == 0)
			return operationCancelled;
		nameOfFieldToUpdate = options.get(selectedOption);
		independentRequired = (HashMap<String, Object>) fieldsMap.get(LMS.independent).get(LMS.required);
		independentOptional = (HashMap<String, Object>) fieldsMap.get(LMS.independent).get(LMS.optional);
		relationalMono = (HashMap<String, Object>) fieldsMap.get(LMS.relational).get(LMS.mono);
		relationalMulti = (HashMap<String, Object>) fieldsMap.get(LMS.relational).get(LMS.multi);
		if (independentRequired.keySet().contains(nameOfFieldToUpdate)) {
			System.out.println(getMonoStringFieldPrompt(objectType, nameOfFieldToUpdate));
			newFieldValueString = scanner.nextLine();
			if (newFieldValueString.isEmpty())
				return operationCancelled;
			independentRequired.put(nameOfFieldToUpdate, newFieldValueString);
			objectToUpdate.setFieldsMap(fieldsMap);
			return adminService.update(objectToUpdate);
		}
		if (independentOptional.keySet().contains(nameOfFieldToUpdate)) {
			if (independentOptional.get(nameOfFieldToUpdate) != null) {
				resetList(options, getChangeOrRemoveOpts(nameOfFieldToUpdate));
				selectedOption = getOptionSelection(genericPrompt, options);
				if (selectedOption == 0)
					return operationCancelled;
				if (selectedOption == 2) {
					independentOptional.put(nameOfFieldToUpdate, null);
					objectToUpdate.setFieldsMap(fieldsMap);
					return adminService.update(objectToUpdate);
				}
			}
			System.out.println(getMonoStringFieldPrompt(objectType, nameOfFieldToUpdate));
			newFieldValueString = scanner.nextLine();
			if (newFieldValueString.isEmpty())
				return operationCancelled;
			independentOptional.put(nameOfFieldToUpdate, newFieldValueString);
			objectToUpdate.setFieldsMap(fieldsMap);
			return adminService.update(objectToUpdate);
		}
		if (relationalMono.keySet().contains(nameOfFieldToUpdate)) {
			if (relationalMono.get(nameOfFieldToUpdate) != null) {
				resetList(options, getChangeOrRemoveOpts(nameOfFieldToUpdate));
				selectedOption = getOptionSelection(genericPrompt, options);
				if (selectedOption == 0)
					return operationCancelled;
				if (selectedOption == 2) {
					relationalMono.put(nameOfFieldToUpdate, null);
					objectToUpdate.setFieldsMap(fieldsMap);
					return adminService.update(objectToUpdate);
				}
			}
			System.out.println(getMonoStringFieldPrompt(objectType, nameOfFieldToUpdate));
			possiblyRelatedObjects = adminService.getAllObjects(nameOfFieldToUpdate);
			possiblyRelatedObjects.add(0, null);
			options.clear();
			options.addAll(possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
					.collect(Collectors.toCollection(ArrayList::new)));
			selectedOption = getOptionSelection(getMonoObjectFieldPrompt(objectType, nameOfFieldToUpdate), options);
			if (selectedOption == 0)
				return operationCancelled;
			newFieldValueObject = possiblyRelatedObjects.get(selectedOption);
			relationalMono.put(nameOfFieldToUpdate, newFieldValueObject);
			objectToUpdate.setFieldsMap(fieldsMap);
			return adminService.update(objectToUpdate);
		}
		if (relationalMulti.keySet().contains(nameOfFieldToUpdate)) {
			valuesOfFieldToUpdate = (ArrayList<LMSObject>) relationalMulti.get(nameOfFieldToUpdate);
			if (valuesOfFieldToUpdate.size() != 0) {
				resetList(options, getAddOrRemoveOpts(nameOfFieldToUpdate));
				selectedOption = getOptionSelection(genericPrompt, options);
				if (selectedOption == 0)
					return operationCancelled;
				if (selectedOption == 2) {
					options.clear();
					options.addAll(valuesOfFieldToUpdate.stream().map(object -> object.getDisplayName())
							.collect(Collectors.toCollection(ArrayList::new)));
					options.add(0, cancelOperation);
					valuesOfFieldToUpdate.add(0, null);
					selectedOptions = getMultiOptionSelection(getRemoveMultiFieldPrompt(nameOfFieldToUpdate), options);
					if (selectedOptions.contains(0))
						return operationCancelled;
					for (int selectionNumber : selectedOptions)
						valuesOfFieldToUpdate.remove(selectionNumber);
					valuesOfFieldToUpdate.remove(null);
					objectToUpdate.setFieldsMap(fieldsMap);
					return adminService.update(objectToUpdate);
				}
				possiblyRelatedObjects = adminService.getAllObjects(objectType);
				for (LMSObject objectAlreadyPresent : valuesOfFieldToUpdate)
					possiblyRelatedObjects.removeIf(object -> object.equals(objectAlreadyPresent));
				options.clear();
				options.addAll(possiblyRelatedObjects.stream().map(object -> object.getDisplayName())
						.collect(Collectors.toCollection(ArrayList::new)));
				options.add(0, cancelOperation);
				possiblyRelatedObjects.add(0, null);
				selectedOptions = getMultiOptionSelection(getAddMultiFieldPrompt(nameOfFieldToUpdate), options);
				if (selectedOptions.contains(0))
					return operationCancelled;
				selectedOptions.stream().forEach(index -> valuesOfFieldToUpdate.add(possiblyRelatedObjects.get(index)));
				objectToUpdate.setFieldsMap(fieldsMap);
				return adminService.update(objectToUpdate);
			}
		}
		return "Oops, something went wrong.";
	}

	/**
	 * Deletes an object in the database
	 * 
	 * @param objectType The type of object to delete
	 * @return A string indicating whether the operation succeeded
	 */
	private String deleteObject(String objectType) {
		int selectedOption;
		LMSObject objectToDelete;
		ArrayList<LMSObject> deletionCandidates = adminService.getAllObjects(objectType);
		ArrayList<String> options = deletionCandidates.stream().map(object -> object.getDisplayName())
				.collect(Collectors.toCollection(ArrayList::new));
		options.add(0, cancelOperation);
		deletionCandidates.add(null);
		selectedOption = getOptionSelection(getObjectDeletionPrompt(objectType), options);
		if (selectedOption == 0)
			return operationCancelled;
		objectToDelete = deletionCandidates.get(selectedOption);
		return (adminService.delete(objectToDelete));
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
				// must change to only show branches with available books
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
				// must change to only show branches with active loans
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

}

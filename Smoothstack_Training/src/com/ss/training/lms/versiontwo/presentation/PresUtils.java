package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.BorrowerService;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.LMSObject;

/**
 * @author Justin O'Brien
 */
public class PresUtils {

	/**
	 * Gets a menu option selection from the user
	 * 
	 * @param prompt  The prompt to show the user
	 * @param options The options the user is to select from
	 * @return The number corresponding to the option the user selects
	 */
	protected static int getOptionSelection(String prompt, List<String> options) {
		int i, selectionNumber;
		for (;;) {
			System.out.println(prompt);
			for (i = 0; i < options.size(); i++) {
				System.out.println(i + ") " + options.get(i));
			}
			try {
				selectionNumber = Integer.parseInt(Presentation.scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(Presentation.invalidSelection);
				continue;
			}
			if (0 <= selectionNumber && selectionNumber < options.size())
				return selectionNumber;
			System.out.println(Presentation.invalidSelection);
		}
	}

	protected static int getNaturalNumber(String prompt, String errorMessage) {
		int result;
		for (;;) {
			System.out.println(prompt);
			try {
				result = Integer.parseInt(Presentation.scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(errorMessage);
				continue;
			}
			if (result >= 0)
				return result;
			System.out.println(errorMessage);
		}
	}

	protected static String changeCopiesNumberPrompt(int currentNumberOfCopies) {
		return "Our records indicate that you currently have " + currentNumberOfCopies
				+ (currentNumberOfCopies == 1 ? " copy" : " copies")
				+ " of this book. What is the new number of copies?";
	}

	/**
	 * @param branchPk The primary key of the branch being updated
	 * 
	 * @return String stating which branch is being updated and telling the user how
	 *         to cancel the update
	 */
	protected static String getBranchUpdateInfo(Branch branch, String cancelCode) {
		return "Updating branch: " + branch.getDisplayName() + " (#" + branch.getId() + ")\nEnter " + cancelCode
				+ " at any prompt to cancel the operation.";
	}

	protected static String getStringWithMaxLength(String prompt, String fieldName, int maxLength) {
		String result;
		for (;;) {
			System.out.println(prompt);
			result = Presentation.scanner.nextLine();
			if (result.length() <= maxLength)
				return result;
			System.out.println("Error: Maximum " + fieldName + " length is " + maxLength + "characters.");
		}
	}

	protected static LMSObject getLMSObjectSelection(List<LMSObject> lmsObjects, String prompt, String negativeOption) {
		int selectedOption;
		ArrayList<String> options = lmsObjects.stream().map(object -> object.getDisplayName())
				.collect(Collectors.toCollection(ArrayList::new));
		lmsObjects.add(0, null);
		options.add(0, negativeOption);
		selectedOption = getOptionSelection(prompt, options);
		return lmsObjects.get(selectedOption);
	}

	/**
	 * Gets the user's card number
	 * 
	 * @param prompt The prompt to show the user
	 * @return The card number input by the user, or 0 if the user wants to go back
	 *         to the previous menu
	 */
	protected static Borrower getBorrowerByCardNumber(String prompt) {
		int cardNumber;
		ArrayList<Borrower> borrowers = (ArrayList<Borrower>) new BorrowerService().getAllObjects(LMS.borrower);
		for (;;) {
			System.out.println(prompt);
			try {
				cardNumber = Integer.parseInt(Presentation.scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(Presentation.invalidCard);
				continue;
			}
			if (cardNumber == 0)
				return null;
			for (Borrower borrower : borrowers)
				if (cardNumber == borrower.getCardNo())
					return borrower;
			System.out.println(Presentation.invalidCard);
		}
	}
}
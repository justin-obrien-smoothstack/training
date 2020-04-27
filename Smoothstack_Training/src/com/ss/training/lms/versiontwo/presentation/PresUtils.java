package com.ss.training.lms.versiontwo.presentation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.business.LMSService;
import com.ss.training.lms.versiontwo.object.LMSObject;

/**
 * @author Justin O'Brien
 */
public class PresUtils {
	
	private static LMSService lmsService = new LMSService();
	private static final Scanner scanner = new Scanner(System.in);
	
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
				selectionNumber = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println(Presentation.invalidSelection);
				continue;
			}
			if (0 <= selectionNumber && selectionNumber < options.size())
				return selectionNumber;
			System.out.println(Presentation.invalidSelection);
		}
	}
	
	public static LMSObject getLMSObjectSelection(String objectType, String prompt, String negativeOption) {
		ArrayList<LMSObject> lmsObjects = lmsService.getAllObjects(objectType);
		ArrayList<String> options = lmsObjects.stream().map(object -> object.getDisplayName()).collect(Collectors.toCollection(ArrayList::new));
		lmsObjects.add(0, null);
		options.add(0, negativeOption);
		selectedOption = getOptionSelection
	}
}

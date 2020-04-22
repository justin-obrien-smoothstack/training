package com.ss.training.javaeightfeatures.stringsorter;

import java.util.Arrays;

/**
 * @author Justin O'Brien
 *
 */
public class StringSorter {

	public static String[] sortByLength(String[] stringsToSort) {
		Arrays.sort(stringsToSort, (stringOne, stringTwo) -> stringOne.length() - stringTwo.length());
		return stringsToSort;
	}

	public static String[] sortByReverseLength(String[] stringsToSort) {
		Arrays.sort(stringsToSort, (stringOne, stringTwo) -> stringTwo.length() - stringOne.length());
		return stringsToSort;
	}

	public static String[] sortAlphabeticallyByFirstCharacterOnly(String[] stringsToSort) {
		Arrays.sort(stringsToSort, (stringOne, stringTwo) -> {
			char charOne, charTwo;
			try {
				charOne = stringOne.toLowerCase().charAt(0);
				charTwo = stringTwo.toLowerCase().charAt(0);
			} catch (StringIndexOutOfBoundsException e) {
				return 0;
			}
			if (charOne < 'a' || charOne > 'z' || charTwo < 'a' || charTwo > 'z')
				return 0;
			return charOne - charTwo;
		});
		return stringsToSort;
	}

	public static String[] sortByPresenceOfLowercaseEWithoutHelper(String[] stringsToSort) {
		final String specialSubstring = "e";
		Arrays.sort(stringsToSort, (stringOne, stringTwo) -> {
			byte comparisonResult = 0;
			if (stringOne.contains(specialSubstring))
				comparisonResult -= 1;
			if (stringTwo.contains(specialSubstring))
				comparisonResult += 1;
			return comparisonResult;
		});
		return stringsToSort;
	}

	public static String[] sortByPresenceOfLowercaseEWithHelper(String[] stringsToSort) {
		Arrays.sort(stringsToSort, (stringOne, stringTwo) -> Utils.sortByPresenceOfLowercaseE(stringOne, stringTwo));
		return stringsToSort;
	}

	public static void main(String[] args) {
		String[] stringsToSort = { "Java", "JUnit", "agile", "scrum", "SQL", "Spring", "Angular", "REST", "JavaScript",
				"Node.js" };
		System.out.println("- Initial array:");
		Utils.printStringArray(stringsToSort.clone());
		System.out.println("- By length:");
		Utils.printStringArray(sortByLength(stringsToSort.clone()));
		System.out.println("- By reverse length:");
		Utils.printStringArray(sortByReverseLength(stringsToSort.clone()));
		System.out.println("- Alphabetically by first character only:");
		Utils.printStringArray(sortAlphabeticallyByFirstCharacterOnly(stringsToSort.clone()));
		System.out.println("- By presence of lowercase e, without static helper method");
		Utils.printStringArray(sortByPresenceOfLowercaseEWithoutHelper(stringsToSort.clone()));
		System.out.println("- By presence of lowercase e, with static helper method");
		Utils.printStringArray(sortByPresenceOfLowercaseEWithHelper(stringsToSort.clone()));
	}
}

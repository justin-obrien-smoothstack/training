package com.ss.training.javaeightfeatures.stringsorter;

/**
 * @author Justin O'Brien
 *
 */
public class Utils {

	public static void printStringArray(String[] stringsToPrint) {

		for (String string : stringsToPrint) {
			System.out.println(string);
		}
		System.out.println();
	}

	public static byte sortByPresenceOfLowercaseE(String stringOne, String stringTwo) {
		final String specialSubstring = "e";
		byte comparisonResult = 0;
		if (stringOne.contains(specialSubstring))
			comparisonResult -= 1;
		if (stringTwo.contains(specialSubstring))
			comparisonResult += 1;
		return comparisonResult;
	}
}

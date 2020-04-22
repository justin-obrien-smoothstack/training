package com.ss.training.javaeightfeatures.stringfilter;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Justin O'Brien
 *
 */
public class StringFilter {
	public static List<String> filterStrings(List<String> stringsToFilter) {
		final char desiredFirstChar = 'a';
		final int desiredLength = 3;
		return stringsToFilter.stream()
				.filter(string -> string.charAt(0) == desiredFirstChar && string.length() == desiredLength)
				.collect(Collectors.toList());
	}
}

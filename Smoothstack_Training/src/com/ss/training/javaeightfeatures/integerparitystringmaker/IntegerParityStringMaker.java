package com.ss.training.javaeightfeatures.integerparitystringmaker;

import java.util.List;

/**
 * @author Justin O'Brien
 *
 */
public class IntegerParityStringMaker {
	static public String makeString(List<Integer> integersToMakeStringFrom) {
		String resultWithExtraComma = integersToMakeStringFrom.stream()
				.map(integer -> ((integer % 2 == 0 ? "e" : "o") + Integer.toString(integer) + ","))
				.reduce("", String::concat);
		return resultWithExtraComma.substring(0, resultWithExtraComma.length() - 1);
	}
}

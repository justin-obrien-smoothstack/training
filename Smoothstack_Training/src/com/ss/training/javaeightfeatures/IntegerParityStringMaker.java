package com.ss.training.javaeightfeatures;

import java.util.List;

/**
 * @author Justin O'Brien
 *
 */
public class IntegerParityStringMaker {
	static public String makeString(List<Integer> integers) {
		String resultWithExtraComma = integers.stream()
				.map(integer -> ((integer % 2 == 0 ? "e" : "o") + Integer.toString(integer) + ","))
				.reduce("", String::concat);
		return resultWithExtraComma.substring(0, resultWithExtraComma.length() - 1);
	}
}

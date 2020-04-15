package com.ss.training.assignment1task1;

public class Main {

	private static void simpleLine(int length, char character) {
		for (int i = 1; i <= length; i++)
			System.out.print(character);
	}

	public static void main(String[] args) {
		int iterations = 4, baseLength = 8, patternHeight = 4, patternIndent = 0, changeIndentationAt = 3,
				extraCharInt = 4;
		char numberingChar = ')', simpleLineChar = '.', linePatternChar = '*', indentChar = ' ', extraChar = '|';
		boolean needExtraChar = false;
		String numberingLine;
		for (int i = 1; i <= iterations; i++) {
			/*
			 * I don't like this way of deciding when to change the indentation. It would be
			 * better to query the LinePattern class for the alignment of the pattern
			 * corresponding to i; I didn't have time to implement that though.
			 */
			if (i == changeIndentationAt)
				patternIndent = (baseLength + i - (2 * patternHeight - 1)) / 2;
			numberingLine = Integer.toString(i) + numberingChar;
			if (needExtraChar && i == extraCharInt)
				numberingLine += extraChar;
			System.out.println(numberingLine);
			if (i % 2 == 0) {
				simpleLine(baseLength + i, simpleLineChar);
				System.out.println();
			}
			LinePattern.print(patternHeight, patternIndent, linePatternChar, indentChar);
			if (i % 2 == 1) {
				System.out.println();
				simpleLine(baseLength + i, simpleLineChar);
			}
			if (i < iterations)
				System.out.println('\n');
		}

	}

}

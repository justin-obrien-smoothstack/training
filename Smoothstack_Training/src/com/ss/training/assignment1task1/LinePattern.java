package com.ss.training.assignment1task1;

public class LinePattern {

	private static int patternCounter = 0;

	private static void printIndentation(int indent, char indentChar) {
		for (int i = 1; i <= indent; i++)
			System.out.print(indentChar);
	}

	private static void pattern1(int height, int indent, char patternChar, char indentChar) {
		for (int i = 1; i <= height; i++) {
			printIndentation(indent, indentChar);
			for (int j = 1; j <= i; j++)
				System.out.print(patternChar);
			if (i < height)
				System.out.println();
		}
	}

	private static void pattern2(int height, int indent, char patternChar, char indentChar) {
		for (int i = 1; i <= height; i++) {
			printIndentation(indent, indentChar);
			for (int j = height; j >= i; j--)
				System.out.print(patternChar);
			if (i < height)
				System.out.println();
		}
	}

	private static void pattern3(int height, int indent, char patternChar, char indentChar) {
		int j;
		for (int i = 1; i <= height; i++) {
			printIndentation(indent, indentChar);
			for (j = height; j > i; j--)
				System.out.print(indentChar);
			for (j = 1; j < 2 * i; j++)
				System.out.print(patternChar);
			if (i < height)
				System.out.println();
		}
	}

	private static void pattern4(int height, int indent, char patternChar, char indentChar) {
		int j;
		for (int i = 1; i <= height; i++) {
			printIndentation(indent, indentChar);
			for (j = 1; j < i; j++)
				System.out.print(indentChar);
			for (j = 2 * height; j >= 2 * i; j--)
				System.out.print(patternChar);
			if (i < height)
				System.out.println();
		}
	}

	public static void print(int height, int indent, char patternChar, char indentChar) {
		patternCounter += 1;
		switch (patternCounter % 4) {
		case 1:
			pattern1(height, indent, patternChar, indentChar);
			break;
		case 2:
			pattern2(height, indent, patternChar, indentChar);
			break;
		case 3:
			pattern3(height, indent, patternChar, indentChar);
			break;
		case 0:
			pattern4(height, indent, patternChar, indentChar);
			break;
		}
	}
}

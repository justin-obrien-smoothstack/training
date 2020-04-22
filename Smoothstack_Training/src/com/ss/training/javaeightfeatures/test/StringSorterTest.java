package com.ss.training.javaeightfeatures.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

import com.ss.training.javaeightfeatures.StringSorter;

/**
 * These tests don't really work because the ordering of elements that compare
 * equal isn't necessarily the same as in the starting array.
 * 
 * @author Justin O'Brien
 *
 */
public class StringSorterTest {

	static String[] stringsToSort = { "Java", "JUnit", "agile", "scrum", "SQL", "MySQL", "Spring", "Angular",
			"JavaScript", "Node.js" };

	@Test
	public void sortByLengthTest() {
		String[] expected = { "SQL", "Java", "JUnit", "agile", "scrum", "MySQL", "Spring", "Angular", "Node.js",
				"JavaScript" };
		assertArrayEquals(expected, StringSorter.sortByLength(stringsToSort));
	}

	@Test
	public void sortByReverseLengthTest() {
		String[] expected = { "JavaScript", "Angular", "Node.js", "Spring", "JUnit", "agile", "scrum", "MySQL", "Java",
				"SQL" };
		assertArrayEquals(expected, StringSorter.sortByReverseLength(stringsToSort));
	}

	@Test
	public void sortAlphabeticallyByFirstCharacterOnlyTest() {
		String[] expected = { "agile", "Angular", "Java", "JUnit", "JavaScript", "MySQL", "Node.js", "scrum", "SQL",
				"Spring" };
		assertArrayEquals(expected, StringSorter.sortAlphabeticallyByFirstCharacterOnly(stringsToSort));
	}
}

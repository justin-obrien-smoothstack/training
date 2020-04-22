package com.ss.training.javaeightfeatures.stringsorter.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import com.ss.training.javaeightfeatures.stringsorter.StringSorter;

/**
 * @author Justin O'Brien
 */
public class StringSorterTest {

	static String[] stringsToSort;

	@Before
	public void before() {
		stringsToSort = new String[] { "Java", "JUnit", "agile", "scrum", "SQL", "MySQL", "Spring", "Angular",
				"JavaScript", "Node.js" };
	}

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

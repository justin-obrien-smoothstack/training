package com.ss.training.javaeightfeatures.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ss.training.javaeightfeatures.StringFilter;

/**
 * @author Justin O'Brien
 *
 */
public class StringFilterTest {

	@Test
	public void filterStringsTest() {
		String[] stringsToFilterAsArray = { "Java", "JUnit", "agile", "scrum", "SQL", "MySQL", "Spring", "Angular",
				"JavaScript", "Node.js", "apt", "AWS", "add" };
		List<String> stringsToFilter = new ArrayList<String>(), expected = new ArrayList<String>();
		for (String string : stringsToFilterAsArray)
			stringsToFilter.add(string);
		expected.add("apt");
		expected.add("add");
		assertEquals(expected, StringFilter.filterStrings(stringsToFilter));
	}
}

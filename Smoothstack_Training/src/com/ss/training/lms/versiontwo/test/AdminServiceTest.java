package com.ss.training.lms.versiontwo.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.AdminService;
import com.ss.training.lms.versiontwo.object.Author;

/**
 * @author Justin O'Brien
 */
public class AdminServiceTest {

	/**
	 * 
	 */
	boolean containsAuthor(ArrayList<Author> authors, Author author) {
		for (Author thisAuthor : authors)
			if (thisAuthor.getName().equals(author.getName()) && thisAuthor.getBookIds().equals(author.getBookIds()))
				return true;
		return false;
	}

	@Test
	public void authorTest() {
		String testName = "Test Name";
		Author author = new Author();
		ArrayList<Author> allAuthors;
		AdminService adminService = new AdminService();
		author.setName(testName);
		author.getBookIds().add(1);
		adminService.createAuthor(author);
		allAuthors = (ArrayList<Author>) adminService.getAllObjects(LMS.author);
		assertTrue(containsAuthor(allAuthors, author));
		for (Author thisAuthor : allAuthors)
			if (thisAuthor.getName().equals(testName)) {
				adminService.deleteAuthor(thisAuthor);
			}
		allAuthors = (ArrayList<Author>) adminService.getAllObjects(LMS.author);
		assertFalse(containsAuthor(allAuthors, author));
	}

}

package com.ss.training.lms.versiontwo.business;

import java.util.ArrayList;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.LMSObject;

/**
 * Provides business logic for LMS functions available to users who are
 * librarians
 * 
 * @author Justin O'Brien
 */
public class LibrarianService extends LMSService {

	public ArrayList<LMSObject> getAllBranches() {
		return getAllObjects(LMS.branch);
	}
	
	/**
	 * Updates the name or address of a branch for a librarian
	 * 
	 * @return A message to tell the user whether the operations succeeded
	 */
	public String updateBranch(Branch branchToUpdate) {
		return ""; // placeholder
	}

	/**
	 * Gets primary keys and names of all library branches
	 * 
	 * @return Primary keys (first row) and names (second row) of branches
	 */
	public Object[][] getBranchPksAndNames() {
		return null; // placeholder
	}

	/**
	 * Gets the name of a branch given its primary key
	 * 
	 * @param branchPk The branch's primary key
	 * @return The branch's name
	 */
	public String getBranchName(int branchPk) {
		return null; // placeholder
	}

	

	/**
	 * Gets primary keys, titles, and authors of all books in the database
	 * 
	 * @return The primary keys of all books in the database (row 0) and strings
	 *         containing their titles and authors (row 1)
	 */
	public Object[][] getAllBookPksTitlesAndAuthors() {
		return null; // placeholder
	}

	/**
	 * Updates the number of copies of a book at a library branch
	 * 
	 * @param branchPk          The primary key of the branch
	 * @param bookPk            The primary key of the book
	 * @param newNumberOfCopies The new number of copies of the book at the branch
	 * @return A string indicating whether the operation succeeded
	 */
	public String updateNumberOfCopies(int branchPk, int bookPk, int newNumberOfCopies) {
		return null; // placeholder
	}

}

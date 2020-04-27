package com.ss.training.lms.versiontwo.business;

import java.sql.Connection;
import java.sql.SQLException;

import com.ss.training.lms.versiontwo.business.dao.BranchDAO;
import com.ss.training.lms.versiontwo.business.dao.CopiesDAO;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;

/**
 * Provides business logic for LMS functions available to users who are
 * librarians
 * 
 * @author Justin O'Brien
 */
public class LibrarianService extends LMSService {

	/**
	 * Updates the name or address of a branch for a librarian
	 * 
	 * @return A message to tell the user whether the operations succeeded
	 */
	public String updateBranch(Branch branch) {
		try (Connection connection = getConnection()) {
			new BranchDAO(connection).update(branch);
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "The branch update was unsuccessful.";
		}
		return "The branch update succeeded.";
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
	public String updateCopies(Branch branch, Book book, int numCopies) {
		Copies copies = new Copies();
		copies.setBranchId(branch.getId());
		copies.setBookId(book.getId());
		copies.setCopies(numCopies);
		try (Connection connection = getConnection()) {
			new CopiesDAO(connection).update(copies);
			connection.commit();
		} catch (Exception e) {
			System.out.println("There was an error while attempting to update the number of book copies.");
			e.printStackTrace();
			return "The number of book copies was not successfully changed.";
		}
		branch.getCopies().stream().forEach(thisCopies -> {
			if (thisCopies.getBookId() == book.getId())
				thisCopies.setCopies(numCopies);
		});
		return "The number of book copies was successfully changed.";
	}
}

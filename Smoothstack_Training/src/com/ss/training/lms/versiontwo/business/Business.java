package com.ss.training.lms.versiontwo.business;

import java.util.ArrayList;
import java.util.List;

/**
 * Top level of business tier of LMS application
 * 
 * @author Justin O'Brien
 */
public class Business {

	/**
	 * The single instance of this class
	 */
	static Business instance = null;

	/**
	 * Private constructor to make this class a singleton
	 */
	private Business() {

	}

	/**
	 * Gets the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static Business getInstance() {
		if (instance == null)
			instance = new Business();
		return instance;
	}

//	/**
//	 * Gets a column of a database table as a list of strings
//	 * 
//	 * @param table  The table to get the column from
//	 * @param column The column to get
//	 * @return Array containing all entries of the specified column of the specified
//	 *         table
//	 */
//	public ArrayList<String> getDbColumnAsStrings(String table, String column) {
//		return null; // placeholder
//	}

//	/**
//	 * Gets a column of a database table as a list of integers
//	 * 
//	 * @param table  The table to get the column from
//	 * @param column The column to get
//	 * @return Array containing all entries of the specified column of the specified
//	 *         table
//	 */
//	public ArrayList<Integer> getDbColumnAsIntegers(String table, String column) {
//		return null; // placeholder
//	}

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
	 * Updates the name or address of a branch for a librarian
	 * 
	 * @param newName    The branch's new name
	 * @param newAddress The branch's new address
	 * @param noChange   If newName or newAddress is equal to this, the
	 *                   corresponding attribute of the branch won't be changed
	 * @return A message to tell the user whether the operations succeeded
	 */
	public String librarianUpdateBranch(String newName, String newAddress, String noChange) {
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

	/**
	 * Gets primary keys, titles, and authors of all books available for checkout at
	 * a library branch
	 * 
	 * @param branchPk The primary key of the branch
	 * @return The primary keys of the available books (row 0) and strings
	 *         containing their titles and authors (row 1)
	 */
	public Object[][] getAvailableBookPksTitlesAndAuthors(int branchPk) {
		return null; // placeholder
	}

	/**
	 * Checks out a book from a library branch to a borrower
	 * 
	 * @param cardNumber The borrower's card number
	 * @param branchPk   The primary key of the branch
	 * @param bookPk     The primary key of the book
	 * @return A message to tell the user whether the transaction succeeded
	 */
	public String checkoutBook(int cardNumber, int branchPk, int bookPk) {
		return null; // placeholder
	}

	/**
	 * Gets primary keys, titles, and authors of all books returnable to a library
	 * branch by a borrower
	 * 
	 * @param cardNumber The card number of the borrower
	 * @param branchPk   The primary key of the branch
	 * @return The primary keys of the returnable books (row 0) and strings
	 *         containing their titles and authors (row 1)
	 */
	public Object[][] getReturnableBookPksTitlesAndAuthors(int cardNumber, int branchPk) {
		return null; // placeholder
	}

	/**
	 * Returns a book to a library branch from a borrower
	 * 
	 * @param cardNumber The borrower's card number
	 * @param branchPk   The primary key of the branch
	 * @param bookPk     The primary key of the book
	 * @return A message to tell the user whether the transaction succeeded
	 */
	public String returnBook(int cardNumber, int branchPk, int bookPk) {
		return null; // placeholder
	}
}
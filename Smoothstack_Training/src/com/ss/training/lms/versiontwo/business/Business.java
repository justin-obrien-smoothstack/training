package com.ss.training.lms.versiontwo.business;

/**
 * Top level of business tier of LMS application
 * 
 * @author Justin O'Brien
 */
public class Business {

	/**
	 * Gets a column of a database table as an array
	 * 
	 * @param table  The table to get the column from
	 * @param column The column to get
	 * @return Array containing all entries of the specified column of the specified
	 *         table
	 */
	public static Object[] getDbColumnAsArray(String table, String column) {
		return null; // placeholder
	}

	/**
	 * Gets primary keys, titles, and authors of all books available at a library
	 * branch
	 * 
	 * @param branchPk The primary key of the branch
	 * @return Primary keys and title/author strings for all books available at the
	 *         branch
	 */
	public static Object[][] getAvailableBooks(int branchPk) {
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
	public static String checkoutBook(int cardNumber, int branchPk, int bookPk) {
		return null; // placeholder
	}
}
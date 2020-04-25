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
	 * Gets a column of a database table as a list of strings
	 * 
	 * @param table  The table to get the column from
	 * @param column The column to get
	 * @return Array containing all entries of the specified column of the specified
	 *         table
	 */
	public static ArrayList<String> getDbColumnAsStrings(String table, String column) {
		return null; // placeholder
	}

	/**
	 * Gets a column of a database table as a list of integers
	 * 
	 * @param table  The table to get the column from
	 * @param column The column to get
	 * @return Array containing all entries of the specified column of the specified
	 *         table
	 */
	public static ArrayList<Integer> getDbColumnAsIntegers(String table, String column) {
		return null; // placeholder
	}

	/**
	 * Gets the name of a branch given its primary key
	 * 
	 * @param branchPk The branch's primary key
	 * @return The branch's name
	 */
	public static String getBranchName(int branchPk) {
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
	public static String librarianUpdateBranch(String newName, String newAddress, String noChange) {
		return null; // placeholder
	}

	/**
	 * Gets primary keys of all books available at a library branch
	 * 
	 * @param branchPk The primary key of the branch
	 * @return Primary keys for all books available at the branch
	 */
	public static ArrayList<Integer> getAvailableBookPks(int branchPk) {
		return null; // placeholder
	}

	/**
	 * Gets title/author strings for all books available at a library branch
	 * 
	 * @param branchPk The primary key of the branch
	 * @return Title/author strings for all books available at the branch
	 */
	public static ArrayList<String> getAvailableBookTitlesAndAuthors(int branchPk) {
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

	/**
	 * Gets primary keys of all books returnable to a library branch by a borrower
	 * 
	 * @param branchPk The primary key of the branch
	 * @return Primary keys for all books available at the branch
	 */
	public static ArrayList<Integer> getReturnableBookPks(int cardNumber, int branchPk) {
		return null; // placeholder
	}

	/**
	 * Gets title/author strings for all books returnable to a library branch by a
	 * borrower
	 * 
	 * @param branchPk The primary key of the branch
	 * @return Title/author strings for all books available at the branch
	 */
	public static ArrayList<String> getReturnableBookTitlesAndAuthors(int cardNumber, int branchPk) {
		return null; // placeholder
	}
}
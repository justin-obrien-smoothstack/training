package com.ss.training.lms.versiontwo.business;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.objects.LMSObject;

/**
 * Provides business logic for LMS functions available to users who are administrators
 * 
 * @author Justin O'Brien
 */
public class AdminService {

	/**
	 * The single instance of this class
	 */
	static AdminService instance = null;

	/**
	 * Private constructor to make this class a singleton
	 */
	private AdminService() {

	}

	/**
	 * Gets the single instance of this class
	 * 
	 * @return The single instance of this class
	 */
	public static AdminService getInstance() {
		if (instance == null)
			instance = new AdminService();
		return instance;
	}
	
	public LMSObject getBlankObject(String objectType) {
		switch(objectType) {
		case LMS.book:
			return new Book();
		case LMS.author:
			return new Author();
		case LMS.publisher:
			return new Publisher();
		case LMS.genre:
			return new Genre();
		case LMS.borrower:
			return new Borrower();
		case LMS.branch:
			return new Branch();
		case LMS.loan:
			return new Loan();
		case LMS.copies:
			return new Copies();
		}
		return null;
	}
	
	/**
	 * Gets objects and descriptions of all loans for which the date in is null or later than the due date
	 * 
	 * @return Loan objects (row 0) and strings describing them (row 1)
	 */
	public Object[][] getOverridableLoansAndDescriptions(){
		return null; // placeholder
	}
	
}

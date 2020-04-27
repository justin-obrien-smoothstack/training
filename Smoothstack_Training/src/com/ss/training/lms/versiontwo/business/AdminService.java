package com.ss.training.lms.versiontwo.business;

import java.util.ArrayList;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.object.Author;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;
import com.ss.training.lms.versiontwo.object.Genre;
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;
import com.ss.training.lms.versiontwo.object.Publisher;

/**
 * Provides business logic for LMS functions available to users who are administrators
 * 
 * @author Justin O'Brien
 */
public class AdminService {

	public String create(LMSObject newObject) {
		return null; // placeholder
	}
	
	public String update(LMSObject newObject) {
		return null; // placeholder
	}
	
	public String delete(LMSObject newObject) {
		return null; // placeholder
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
	
	public ArrayList<LMSObject> getAllObjects(String objectType){
		return null; // placeholder
	}
	
	/**
	 * Gets objects and descriptions of all loans for which the date in is null or later than the due date
	 * 
	 * @return Loan objects (row 0) and strings describing them (row 1)
	 */
	public Object[][] getOverridableLoansAndDescriptions(){
		return null; // placeholder
	}
	
	public String overrideDueDate(Loan loanToOveride) {
		return ""; // placeholder
	}
	
}

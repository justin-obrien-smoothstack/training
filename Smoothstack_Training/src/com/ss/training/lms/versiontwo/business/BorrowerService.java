package com.ss.training.lms.versiontwo.business;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.dao.CopiesDAO;
import com.ss.training.lms.versiontwo.business.dao.LoanDAO;
import com.ss.training.lms.versiontwo.object.Book;
import com.ss.training.lms.versiontwo.object.Borrower;
import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;
import com.ss.training.lms.versiontwo.object.LMSObject;
import com.ss.training.lms.versiontwo.object.Loan;

/**
 * Provides business logic for LMS functions available to users who are
 * borrowers
 * 
 * @author Justin O'Brien
 */
public class BorrowerService extends LMSService {

	public ArrayList<LMSObject> getBranchesWithBooks() {
		return ((ArrayList<LMSObject>) getAllObjects(LMS.branch)).stream().filter(branch -> {
			for (Copies copies : ((Branch) branch).getCopies())
				if (copies.getCopies() != 0)
					return true;
			return false;
		}).collect(Collectors.toCollection(ArrayList::new));
	}

	public ArrayList<LMSObject> getAvailableBooks(Branch branch) {
		ArrayList<Integer> availableBookIds = new ArrayList<Integer>();
		branch.getCopies().stream().filter(copies -> copies.getCopies() != 0)
				.forEach(copies -> availableBookIds.add(copies.getBookId()));
		return (ArrayList<LMSObject>) getObjectsById(LMS.book, availableBookIds);
	}

	/**
	 * Checks out a book from a library branch to a borrower
	 * 
	 * @return A message to tell the user whether the transaction succeeded
	 */
	public String checkoutBook(Borrower borrower, Branch branch, Book book) {
		Loan loan = new Loan();
		loan.setCardNo(borrower.getCardNo());
		loan.setBranchId(branch.getId());
		loan.setBookId(book.getId());
		loan.setDateOut(LocalDateTime.now());
		loan.setDueDate(loan.getDateOut().plusDays(7));
		branch.getCopies().stream().filter(copies -> copies.getBookId() == book.getId())
				.forEach(copies -> copies.setCopies(copies.getCopies() - 1));
		branch.getLoans().add(loan);
		return(updateBranch(branch));
	}

	/**
	 * @return Card numbers of all borrowers
	 */
	public ArrayList<Integer> getCardNumbers() {
		return new ArrayList<Integer>(); // placeholder
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

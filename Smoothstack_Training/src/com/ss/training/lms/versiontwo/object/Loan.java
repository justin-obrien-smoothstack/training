package com.ss.training.lms.versiontwo.object;

import java.time.LocalDateTime;

import com.ss.training.lms.versiontwo.LMS;
import com.ss.training.lms.versiontwo.business.LMSService;

/**
 * @author Justin O'Brien
 */
public class Loan extends LMSObject {

	private int bookId, branchId, cardNo;

	private LocalDateTime dateOut, dueDate, dateIn;

	@Override
	public String getDisplayName() {
		LMSService service = new LMSService();
		Borrower borrower = (Borrower) service.getObjectById(LMS.borrower, cardNo);
		Branch branch = (Branch) service.getObjectById(LMS.branch, branchId);
		Book book = (Book) service.getObjectById(LMS.book, bookId);
		return borrower.getDisplayName() + " checked out " + book.getDisplayName() + " from " + branch.getDisplayName()
				+ " on " + dateOut;
	}

	/**
	 * @return the bookId
	 */
	public int getBookId() {
		return bookId;
	}

	/**
	 * @param bookId the bookId to set
	 */
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	/**
	 * @return the cardNo
	 */
	public int getCardNo() {
		return cardNo;
	}

	/**
	 * @param cardNo the cardNo to set
	 */
	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * @return the dateOut
	 */
	public LocalDateTime getDateOut() {
		return dateOut;
	}

	/**
	 * @param dateOut the dateOut to set
	 */
	public void setDateOut(LocalDateTime dateOut) {
		this.dateOut = dateOut;
	}

	/**
	 * @return the dueDate
	 */
	public LocalDateTime getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the dateIn
	 */
	public LocalDateTime getDateIn() {
		return dateIn;
	}

	/**
	 * @param dateIn the dateIn to set
	 */
	public void setDateIn(LocalDateTime dateIn) {
		this.dateIn = dateIn;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + branchId;
		result = prime * result + cardNo;
		result = prime * result + ((dateOut == null) ? 0 : dateOut.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Loan other = (Loan) obj;
		if (bookId != other.bookId)
			return false;
		if (branchId != other.branchId)
			return false;
		if (cardNo != other.cardNo)
			return false;
		if (dateOut == null) {
			if (other.dateOut != null)
				return false;
		} else if (!dateOut.equals(other.dateOut))
			return false;
		return true;
	}
}

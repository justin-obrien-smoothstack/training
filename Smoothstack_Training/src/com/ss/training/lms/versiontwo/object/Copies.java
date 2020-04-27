package com.ss.training.lms.versiontwo.object;

import java.util.HashMap;

/**
 * @author Justin O'Brien
 */
public class Copies extends LMSObject {

	private int bookId, branchId, copies;
	
	/**
	 * @param bookId
	 * @param branchId
	 * @param copies
	 */
	public Copies(int bookId, int branchId, int copies) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.copies = copies;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bookId;
		result = prime * result + branchId;
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
		Copies other = (Copies) obj;
		if (bookId != other.bookId)
			return false;
		if (branchId != other.branchId)
			return false;
		return true;
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
	 * @return the copies
	 */
	public int getCopies() {
		return copies;
	}

	/**
	 * @param copies the copies to set
	 */
	public void setCopies(int copies) {
		this.copies = copies;
	}

	@Override
	public String getDisplayName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public HashMap<String, HashMap<String, Object>> getFieldsMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFieldsMap(HashMap<String, HashMap<String, Object>> fieldsMap) {
		// TODO Auto-generated method stub

	}

}
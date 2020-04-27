package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Justin O'Brien
 */
public class Branch extends LMSObject {

	private int id;
	private String name, address;
	private ArrayList<Copies> copies;
	private ArrayList<Loan> loans;

	public Branch() {
		copies = new ArrayList<Copies>();
		loans = new ArrayList<Loan>();
	}

	/**
	 * @return the branchId
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param branchId the branchId to set
	 */
	public void setId(int branchId) {
		this.id = branchId;
	}

	/**
	 * @return the branchName
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param branchName the branchName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the branchAddress
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param branchAddress the branchAddress to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the copies
	 */
	public ArrayList<Copies> getCopies() {
		return copies;
	}

	/**
	 * @param copies the copies to set
	 */
	public void setCopies(ArrayList<Copies> copies) {
		this.copies = copies;
	}

	/**
	 * @return the loans
	 */
	public ArrayList<Loan> getLoans() {
		return loans;
	}

	/**
	 * @param loans the loans to set
	 */
	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Branch other = (Branch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String getDisplayName() {
		return (name == null ? "(name not found)" : name) + (address == null ? " (address not found)" : ", " + address);
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

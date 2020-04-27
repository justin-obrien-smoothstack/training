package com.ss.training.lms.versiontwo.object;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Justin O'Brien
 */
public class Branch extends LMSObject {

	private int id;
	private String name, address;
	private HashMap<Integer, Integer> copies;
	private HashMap<HashMap<String, Object>, HashMap<String, LocalDateTime>> loans;

	public Branch() {
		copies = new HashMap<Integer, Integer>();
		loans = new HashMap<HashMap<String, Object>, HashMap<String, LocalDateTime>>();
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
	public void setName(String branchName) {
		this.name = branchName;
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
	public void setAddress(String branchAddress) {
		this.address = branchAddress;
	}

	/**
	 * @return the bookLoans
	 */
	public ArrayList<HashMap<String, Object>> getLoans() {
		return loans;
	}

	/**
	 * @param bookLoans the bookLoans to set
	 */
	public void setLoans(ArrayList<HashMap<String, Object>> bookLoans) {
		this.loans = bookLoans;
	}

	/**
	 * @return the copies
	 */
	public HashMap<Integer, Integer> getCopies() {
		return copies;
	}

	/**
	 * @param copies the copies to set
	 */
	public void setCopies(HashMap<Integer, Integer> copies) {
		this.copies = copies;
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
		if (name == null)
			return "(name not found)";
		return name;
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

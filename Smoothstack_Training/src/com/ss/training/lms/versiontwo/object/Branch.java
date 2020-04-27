package com.ss.training.lms.versiontwo.object;

import java.util.HashMap;

/**
 * @author Justin O'Brien
 */
public class Branch extends LMSObject {

	private int branchId;
	private String name, address;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the branchId
	 */
	public int getBranchId() {
		return branchId;
	}
	
	/**
	 * @return the branchId
	 */
	public void setBranchId(int branchId) {
		this.branchId = branchId;
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

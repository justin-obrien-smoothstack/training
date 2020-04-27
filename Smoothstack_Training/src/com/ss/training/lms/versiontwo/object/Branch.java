package com.ss.training.lms.versiontwo.object;

import java.util.HashMap;

/**
 * @author Justin O'Brien
 */
public class Branch extends LMSObject {

	private int branchId;
	private String name, address;

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

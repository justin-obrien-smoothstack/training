package com.ss.training.lms.versiontwo.object;

import java.util.HashMap;

import com.ss.training.lms.versiontwo.object.LMSObject;

/**
 * @author Justin O'Brien
 */
public class Loan extends LMSObject {

	@Override
	public String getDisplayName() {
		return "";
	}

	@Override
	public HashMap<String, HashMap<String, Object>> getFieldsMap() {
		return new HashMap<String, HashMap<String, Object>>();
	}

	@Override
	public void setFieldsMap(HashMap<String, HashMap<String, Object>> fieldsMap) {

	}

}

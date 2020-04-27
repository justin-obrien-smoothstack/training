package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Justin O'Brien
 */
public abstract class BookOrBranch extends LMSObject {

	public abstract int getId();
	
	public abstract ArrayList<Loan> getLoans();
	
	public abstract void setLoans(ArrayList<Loan> loans);
	
	public abstract ArrayList<Copies> getCopies();
	
	public abstract void setCopies(ArrayList<Copies> copies);
	
	public abstract String getDisplayName();

	public abstract HashMap<String, HashMap<String, Object>> getFieldsMap();

	public abstract void setFieldsMap(HashMap<String, HashMap<String, Object>> fieldsMap);

}

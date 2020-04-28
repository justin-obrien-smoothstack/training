package com.ss.training.lms.versiontwo.object;

import java.util.ArrayList;

/**
 * @author Justin O'Brien
 */
public interface HasCopiesLoansAndIntegerId {

	public int getId();
	
	public void setId(int id);
	
	public ArrayList<Copies> getCopies();

	public void setCopies(ArrayList<Copies> copies);

	public ArrayList<Loan> getLoans();

	public void setLoans(ArrayList<Loan> loans);
}

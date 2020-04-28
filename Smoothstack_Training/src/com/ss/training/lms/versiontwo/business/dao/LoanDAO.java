package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Loan;

/**
 * @author Justin O'Brien
 */
public class LoanDAO extends LMSDAO<Loan> {

	/**
	 * @param connnection
	 */
	public LoanDAO(Connection connnection) {
		super(connnection);
		nativeTable = tblLoans;
	}
	
	public void create() {
		
	}
	
	public void update() {
		
	}
	
	public void delete() {
		
	}

	@Override
	public ArrayList<Loan> extractData(ResultSet resultSet) throws SQLException {
		Loan loan;
		Timestamp dateInResult, dueDateResult;
		ArrayList<Loan> loans = new ArrayList<Loan>();
		while (resultSet.next()) {
			loan = new Loan();
			loan.setBookId(resultSet.getInt(bookId));
			loan.setBranchId(resultSet.getInt(branchId));
			loan.setCardNo(resultSet.getInt(cardNo));
			loan.setDateOut(resultSet.getTimestamp(dateOut).toLocalDateTime());
			dueDateResult = resultSet.getTimestamp(dueDate);
			dateInResult = resultSet.getTimestamp(dateIn);
			loan.setDueDate(dueDateResult == null ? null : dueDateResult.toLocalDateTime());
			loan.setDateIn(dateInResult == null ? null : dateInResult.toLocalDateTime());
			loans.add(loan);
		}
		return loans;
	}

}

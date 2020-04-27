package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Loan> extractData(ResultSet resultSet) throws SQLException {
		Loan loan;
		ArrayList<Loan> loans = new ArrayList<Loan>();
		while(resultSet.next()) {
			loan = new Loan();
			loan.setBookId(resultSet.getInt(bookId));
			loan.setBranchId(resultSet.getInt(branchId));
			loan.setCardNo(resultSet.getInt(cardNo));
			loan.setDateOut(resultSet.getTimestamp(dateOut).toLocalDateTime());
			loan.setDateIn(resultSet.getTimestamp(dateIn).toLocalDateTime());
			loan.setDueDate(resultSet.getTimestamp(dueDate).toLocalDateTime());
			loans.add(loan);
		}
		return loans;
	}

}

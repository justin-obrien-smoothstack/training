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

	public void create(Loan loan) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { loan.getBranchId(), loan.getBookId(), loan.getCardNo(), loan.getDateOut(),
				loan.getDueDate(), loan.getDateIn() };
		save("INSERT INTO " + nativeTable + " (" + branchId + ", " + bookId + cardNo + ", " + dateOut + ", " + dueDate
				+ ", " + dateIn + ") VALUES (?, ?, ?, ?, ?, ?)", queryArgs);
	}

	public void update(Loan loan) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { loan.getBranchId(), loan.getBookId(), loan.getCardNo(), loan.getDateOut(),
				loan.getDueDate(), loan.getDateIn() };
		save("UPDATE " + nativeTable + " SET " + dueDate + " = ?, " + dateIn + " = ? WHERE " + branchId + " = ? AND "
				+ bookId + " = ? AND " + cardNo + " = ? AND " + dateOut + " = ?", queryArgs);
	}

	public void delete(Loan loan) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { loan.getBranchId(), loan.getBookId(), loan.getCardNo(), loan.getDateOut() };
		save("DELETE FROM " + nativeTable + " WHERE " + branchId + " = ? AND " + bookId + " = ? AND " + cardNo
				+ " = ? AND " + dateOut + " = ?", queryArgs);
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

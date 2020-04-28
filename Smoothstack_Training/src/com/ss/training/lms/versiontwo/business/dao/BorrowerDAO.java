package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Borrower;

/**
 * @author Justin O'Brien
 */
public class BorrowerDAO extends LMSDAO<Borrower> {

	/**
	 * @param connnection
	 */
	public BorrowerDAO(Connection connnection) {
		super(connnection);
		nativeTable = tblBorrower;
	}

	public int create(Borrower borrower) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { borrower.getName(), borrower.getAddress(), borrower.getPhone() };
		save("INSERT INTO " + nativeTable + " (" + name + ", " + address + ", " + phone + ") VALUES (?, ?, ?)",
				queryArgs);
		return 0;
	}

	public void update(Borrower borrower) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = { borrower.getName(), borrower.getAddress(), borrower.getPhone(), borrower.getCardNo() };
		save("UPDATE " + nativeTable + " SET " + name + " = ?, " + address + " = ?, " + phone + " = ? WHERE " + cardNo
				+ " = ?", queryArgs);
	}

	public void delete(Borrower borrower) throws ClassNotFoundException, SQLException {
		Object[] queryArgs = {  borrower.getCardNo() };
		save("DELETE FROM " + nativeTable + " WHERE " + cardNo + " = ?", queryArgs);
	}

	@Override
	public ArrayList<Borrower> extractData(ResultSet resultSet) throws SQLException {
		Borrower borrower;
		ArrayList<Borrower> borrowers = new ArrayList<Borrower>();
		while (resultSet.next()) {
			borrower = new Borrower();
			borrower.setCardNo(resultSet.getInt(cardNo));
			borrower.setName(resultSet.getString(name));
			borrower.setAddress(resultSet.getString(address));
			borrower.setPhone(resultSet.getString(phone));
			borrowers.add(borrower);
		}
		return borrowers;
	}

}

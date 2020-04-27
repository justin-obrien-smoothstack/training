package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Loan;

/**
 * @author Justin O'Brien
 */
public class LoansDAO extends LMSDAO<Loan> {

	/**
	 * @param connnection
	 */
	public LoansDAO(Connection connnection) {
		super(connnection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Loan> extractData(ResultSet resultSet) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

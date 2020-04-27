package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ss.training.lms.versiontwo.object.Branch;
import com.ss.training.lms.versiontwo.object.Copies;

/**
 * @author Justin O'Brien
 */
public class CopiesDAO extends LMSDAO<Copies> {

	/**
	 * @param connnection
	 */
	public CopiesDAO(Connection connnection) {
		super(connnection);
		nativeTable = tblCopies;
	}

	@Override
	public ArrayList<Copies> extractData(ResultSet resultSet) throws SQLException {
		Copies copies;
		ArrayList<Copies> copieses = new ArrayList<Copies>();
		while (resultSet.next()) {
			copies = new Copies();
			copies.setBranchId(resultSet.getInt(branchId));
			copies.setBookId(resultSet.getInt(bookId));
			copies.setCopies(resultSet.getInt(noOfCopies));
			copieses.add(copies);
		}
		return copieses;
	}
}

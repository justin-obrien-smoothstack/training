package com.ss.training.lms.versiontwo.business.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		// TODO Auto-generated method stub
		return null;
	}

}
